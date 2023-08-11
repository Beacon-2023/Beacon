package com.BEACON.beacon.scraping.service;

import static com.BEACON.beacon.scraping.dto.DisasterAlertMapper.toEntity;

import com.BEACON.beacon.scraping.dto.DisasterAlertDto;
import com.BEACON.beacon.scraping.repository.ScrapingRepository;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    private final ScrapingRepository repository;

    /**
     * 홈페이지에서 재난 정보를 스크래핑 10초에 한 번씩 실행되도록 스케쥴링되어 있음
     */
    @Scheduled(fixedRate = 10000)
    @Transactional
    public void scrapingDisasterInfo() throws IOException {
        // 국민재난안전포털 쿠키 받아오기
        Map<String, String> cookies = getSafeKoreaCookies();

        // 재난문자 가져오기
        JSONObject searchInfoJson = generatePayloadJson();
        Connection.Response response = sendDisasterRequest(cookies, searchInfoJson);

        JSONObject responseJson = new JSONObject(response.body());

        JSONArray disasterSmsList = responseJson.getJSONArray("disasterSmsList");
        for (int i = 0; i < disasterSmsList.length(); i++) {
            JSONObject obj = disasterSmsList.getJSONObject(i);
            long alertId = obj.getLong("MD101_SN");
            String disasterName = obj.getString("DSSTR_SE_NM");
            String createdAt = obj.getString("CREAT_DT");
            String receivedAreaName = obj.getString("RCV_AREA_NM");
            String content = obj.getString("MSG_CN");

            DisasterAlertDto dto = new DisasterAlertDto(alertId, disasterName, createdAt,
                    receivedAreaName, content);
            if (!isDuplicatedAlert(dto)) {
                repository.save(toEntity(dto));
            }
        }
    }

    private boolean isDuplicatedAlert(DisasterAlertDto dto) {
        return !repository.existsById(dto.getId());
    }

    /**
     * 홈페이지로부터 쿠키 받아오기
     *
     * @return 쿠키
     */
    private Map<String, String> getSafeKoreaCookies() throws IOException {
        return Jsoup.connect(SAFEKOREA_HOME_URL)
                .userAgent(USER_AGENT)
                .timeout(1000)
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,ja;q=0.6")
                .header("Connection", "keep-alive")
                .header("Host", "www.safekorea.go.kr")
                .header("Origin", "https://www.safekorea.go.kr")
                .header("Sec-Ch-Ua",
                        "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
                .header("Sec-Ch-Ua-Mobile", "?0")
                .header("Sec-Ch-Ua-Platform", "\"Windows\"")
                .method(Connection.Method.POST)
                .execute()
                .cookies();
    }

    /**
     * cookies 와 payload 를 인자로 받아서 홈페이지에 request 전송
     *
     * @param cookies        request 와 함께 보내져야 하는 쿠키
     * @param searchInfoJson JSON payload
     * @return 서버로부터 받은 response
     */
    private Connection.Response sendDisasterRequest(Map<String, String> cookies,
            JSONObject searchInfoJson) throws IOException {
        return Jsoup.connect(DISASTER_URL)
                .userAgent(USER_AGENT)
                .timeout(1000)
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,ja;q=0.6")
                .header("Connection", "keep-alive")
                .header("Host", "www.safekorea.go.kr")
                .header("Origin", "https://www.safekorea.go.kr")
                .header("Sec-Ch-Ua",
                        "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
                .header("Sec-Ch-Ua-Mobile", "?0")
                .header("Sec-Ch-Ua-Platform", "\"Windows\"")
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Site", "same-origin")
                .header("Accept", "application/json")
                .header("Content-Length", String.valueOf(searchInfoJson.toString().length()))
                .header("Content-Type", "application/json; charset=UTF-8")
                .header("Referer",
                        "https://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/sfc/dis/disasterMsgList.jsp?menuSeq=679")
                .header("X-Requested-With", "XMLHttpRequest")
                .requestBody(searchInfoJson.toString())
                .cookies(cookies)
                .ignoreContentType(true)
                .method(Connection.Method.POST)
                .execute();
    }

    /**
     * SearchInfo 를 위한 JSON payload 생성
     *
     * @return 생성된 JSON payload
     */
    private JSONObject generatePayloadJson() {
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

}
