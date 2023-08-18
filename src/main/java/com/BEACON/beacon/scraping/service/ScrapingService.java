package com.BEACON.beacon.scraping.service;

import static com.BEACON.beacon.scraping.dto.DisasterAlertMapper.toEntity;

import com.BEACON.beacon.region.domain.RegionAlert;
import com.BEACON.beacon.region.dto.RegionAlertDto;
import com.BEACON.beacon.region.dto.RegionDto;
import com.BEACON.beacon.region.dto.RegionMapper;
import com.BEACON.beacon.region.service.RegionAlertService;
import com.BEACON.beacon.region.service.RegionService;
import com.BEACON.beacon.scraping.domain.DisasterCategory;
import com.BEACON.beacon.scraping.dto.DisasterAlertDto;
import com.BEACON.beacon.scraping.repository.ScrapingRepository;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScrapingService {

    // 국민재난안전포털 메인 페이지
    private static final String SAFEKOREA_HOME_URL = "https://www.safekorea.go.kr/";
    // 국민재난안전포털 재난문자 페이지
    private static final String DISASTER_URL = "https://www.safekorea.go.kr/idsiSFK/sfk/cs/sua/web/DisasterSmsList.do";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
            + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36";

    private final RegionService regionService;

    private final RegionAlertService regionAlertService;

    private final ScrapingRepository repository;

    /**
     * 소스로부터 재난 정보를 10초마다 한 번씩 스크래핑합니다.
     *
     * @throws IOException 데이터를 읽거나 쓰는 도중 오류가 발생하면 IOException이 발생합니다.
     */
    @Scheduled(fixedRate = 10000)
    @Transactional
    public void scrapingDisasterInfo() throws IOException {
        Map<String, String> cookies = getSafeKoreaCookies();

        JSONObject searchInfoJson = generatePayloadJson();
        Connection.Response response = sendDisasterRequest(cookies, searchInfoJson);

        JSONObject responseJson = new JSONObject(response.body());
        JSONArray disasterSmsList = responseJson.getJSONArray("disasterSmsList");

        saveDisasterInfo(disasterSmsList);
    }

    /**
     * 메인 홈페이지로부터 쿠키를 받아옵니다.
     *
     * @return 받아온 쿠키
     */
    Map<String, String> getSafeKoreaCookies() throws IOException {
        return Jsoup.connect(SAFEKOREA_HOME_URL).userAgent(USER_AGENT).timeout(1000)
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,ja;q=0.6")
                .header("Connection", "keep-alive").header("Host", "www.safekorea.go.kr")
                .header("Origin", "https://www.safekorea.go.kr").header("Sec-Ch-Ua",
                        "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
                .header("Sec-Ch-Ua-Mobile", "?0").header("Sec-Ch-Ua-Platform", "\"Windows\"")
                .method(Connection.Method.POST).execute().cookies();
    }

    /**
     * SearchInfo 를 위한 JSON payload를 생성합니다.
     *
     * @return 생성된 JSON payload
     */
    JSONObject generatePayloadJson() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String formattedNow = now.format(formatter);

        JSONObject payloadJson = new JSONObject();
        payloadJson.put("c_ocrc_type", "");
        payloadJson.put("dstr_se_Id", "");
        payloadJson.put("firstIndex", "1");
        payloadJson.put("lastIndex", "1");
        payloadJson.put("pageIndex", "1");
        payloadJson.put("pageSize", "10");
        payloadJson.put("pageUnit", "10");
        payloadJson.put("rcv_Area_Id", "");
        payloadJson.put("recordCountPerPage", "10");
        payloadJson.put("sbLawArea1", "");
        payloadJson.put("sbLawArea2", "");
        payloadJson.put("sbLawArea3", "");
        payloadJson.put("searchBgnDe", formattedNow);
        payloadJson.put("searchEndDe", formattedNow);
        payloadJson.put("searchGb", "1");
        payloadJson.put("searchWrd", "");

        JSONObject searchInfoJson = new JSONObject();
        searchInfoJson.put("searchInfo", payloadJson);
        return searchInfoJson;
    }

    /**
     * cookies 와 payload 를 인자로 받아서 홈페이지에 request를 전송합니다.
     *
     * @param cookies          request 와 함께 보내져야 하는 쿠키
     * @param searchInfoJson   JSON payload
     * @return 서버로부터 받은 response
     */
    Connection.Response sendDisasterRequest(Map<String, String> cookies, JSONObject searchInfoJson)
            throws IOException {
        return Jsoup.connect(DISASTER_URL).userAgent(USER_AGENT).timeout(1000)
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,ja;q=0.6")
                .header("Connection", "keep-alive").header("Host", "www.safekorea.go.kr")
                .header("Origin", "https://www.safekorea.go.kr").header("Sec-Ch-Ua",
                        "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
                .header("Sec-Ch-Ua-Mobile", "?0").header("Sec-Ch-Ua-Platform", "\"Windows\"")
                .header("Sec-Fetch-Dest", "empty").header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Site", "same-origin").header("Accept", "application/json")
                .header("Content-Length", String.valueOf(searchInfoJson.toString().length()))
                .header("Content-Type", "application/json; charset=UTF-8").header("Referer",
                        "https://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/sfc/dis/disasterMsgList.jsp?menuSeq=679")
                .header("X-Requested-With", "XMLHttpRequest").requestBody(searchInfoJson.toString())
                .cookies(cookies).ignoreContentType(true).method(Connection.Method.POST).execute();
    }

    /**
     * API 응답에서 검색된 재난 정보를 저장합니다.
     *
     * @param disasterSmsList 저장할 재난 정보를 포함한 JSONArray
     */
    void saveDisasterInfo(JSONArray disasterSmsList) {
        for (int i = 0; i < disasterSmsList.length(); i++) {
            JSONObject obj = disasterSmsList.getJSONObject(i);
            DisasterAlertDto dto = buildDisasterAlertDto(obj);

            // 중복된 재난의 경우 DB에 저장하지 않는다.
            if (isUniqueAlert(dto)) {
                repository.save(toEntity(dto));
            }
        }
    }

    /**
     * 제공된 JSONObject를 사용하여 DisasterAlertDto 객체를 구성합니다.
     *
     * @param obj DisasterAlertDto를 구성하기 위한 데이터를 포함하는 JSONObject.
     * @return 제공된 JSONObject로 구성된 DisasterAlertDto 객체를 반환합니다.
     */
    DisasterAlertDto buildDisasterAlertDto(JSONObject obj) {
        long alertId = obj.getLong("MD101_SN");

        String disasterName = obj.getString("DSSTR_SE_NM");
        DisasterCategory disasterCategory = mapDisasterNameToCategory(disasterName);

        String createdAt = obj.getString("CREAT_DT");
        String content = obj.getString("MSG_CN");
        List<RegionAlertDto> regionAlertDtoList
            = buildRegionAlertDtoList(obj.getString("RCV_AREA_ID"));

        return new DisasterAlertDto(alertId, disasterCategory, createdAt, content,
                regionAlertDtoList);
    }

    /**
     * 지역 알림 DTO List를 구성합니다.
     *
     * @param receivedRegionCode 수신된 지역 코드 문자열입니다.
     * @return 구성된 지역 알림 DTO 목록을 반환합니다.
     */
    private List<RegionAlertDto> buildRegionAlertDtoList(String receivedRegionCode) {
        List<RegionAlertDto> regionAlertDtoList = new ArrayList<>();
        String[] regionCodes = receivedRegionCode.split(",");
        for (String regionCode : regionCodes) {
            regionAlertDtoList.add(buildRegionAlertDto(Integer.parseInt(regionCode)));
        }

        return regionAlertDtoList;
    }

    /**
     * 지역 알림 DTO를 구성합니다.
     *
     * @param regionCode 지역 코드입니다.
     * @return 구성된 지역 알림 DTO를 반환합니다.
     */
    private RegionAlertDto buildRegionAlertDto(Integer regionCode) {
        RegionDto regionDto = regionService.findRegionByCode(regionCode);
        RegionAlert regionAlert = new RegionAlert();
        regionAlert.setRegion(regionDto);
        regionAlertService.saveRegionAlert(RegionMapper.toDto(regionAlert));

        return RegionMapper.toDto(regionAlert);
    }

    /**
     * 인자로 전달된 재난 이름에 해당하는 재난 카테고리를 매핑하는 메서드입니다.
     *
     * @param disasterName 재난 이름
     * @return 재난 카테고리
     */
    DisasterCategory mapDisasterNameToCategory(String disasterName) {
        switch (disasterName) {
            case "민방공", "비상사태" -> {
                return DisasterCategory.CIVIL_DEFENCE;
            }
            case "산불" -> {
                return DisasterCategory.WILDFIRE;
            }
            case "태풍" -> {
                return DisasterCategory.TYPHOON;
            }
            case "홍수" -> {
                return DisasterCategory.FLOOD;
            }
            case "지진" -> {
                return DisasterCategory.EARTHQUAKE;
            }
            default -> {
                return DisasterCategory.ETC;
            }
        }
    }

    /**
     * 인자로 들어온 DisasterAlertDto가 DB에 이미 저장되어 있는지 여부를 체크합니다.
     *
     * @return true : DB에 이미 값이 존재함
     *         false : 새로운 재난문자
     */
    boolean isUniqueAlert(DisasterAlertDto dto) {
        return !repository.existsById(dto.getId());
    }

}
