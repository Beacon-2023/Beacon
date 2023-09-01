package com.BEACON.beacon.location.service;

import com.BEACON.beacon.global.error.exception.LegalDongCodeNotFoundException;
import com.BEACON.beacon.location.dao.RegionTokenRepository;
import com.BEACON.beacon.location.domain.RegionTokenEntity;
import com.BEACON.beacon.location.response.KakaoApiResponse;
import com.BEACON.beacon.location.response.KakaoDocument;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationApiService {

    @Value("${kakao-api-key}")
    private String kakaoApiKey;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final RegionTokenRepository regionTokenRepository;
    private final String BASE_URL = "https://dapi.kakao.com/v2/local/geo/coord2regioncode";
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();


    /**
     * 위도와 경도를 카카오맵 API에 보내 법정동 코드를 받아옵니다
     *
     * @param longitude
     * @param latitude
     * @return legalDongCode
     * @throws IOException
     */
    public String findLegalDongCode(Double longitude, Double latitude) throws Exception {

        String url = buildUrl(longitude, latitude);
        Request request = createRequest(url);
        try (Response response = client.newCall(request).execute()) {

            if (response.isSuccessful()) {
                return parseLegalDongCode(response);
            } else {
                throw new IOException();
            }

        }
    }


    private String buildUrl(Double longitude, Double latitude) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("x", String.valueOf(longitude));
        urlBuilder.addQueryParameter("y", String.valueOf(latitude));
        return urlBuilder.build().toString();
    }


    private Request createRequest(String url) {
        Request.Builder builder = new Request.Builder().url(url).get();

        builder.addHeader("Authorization", kakaoApiKey);

        return builder.build();
    }


    private String parseLegalDongCode(Response response) throws IOException {
        ResponseBody body = response.body();
        if (body != null) {

            KakaoApiResponse apiResponse = gson.fromJson(body.string(), KakaoApiResponse.class);

            List<KakaoDocument> documentList = apiResponse.getDocuments();
            //법정동 코드 얻기
            String legalDongCode = documentList.stream()
                    .filter((document) -> "B".equals(document.getRegionType()))
                    .map(KakaoDocument::getCode)
                    .collect(Collectors.joining());


            return Optional.ofNullable(legalDongCode)
                    .orElseThrow(() -> new LegalDongCodeNotFoundException("법정동코드를 찾을 수 없습니다"));

        } else {
            throw new IOException("No Response body");
        }
    }


    /**
     * @param fcmToken
     * @param legalDongCode
     */
    @Transactional
    public void storeRegionAndFcmToken(String fcmToken, String legalDongCode) {

        RegionTokenEntity regionToken = regionTokenRepository.findById(fcmToken).orElse(new RegionTokenEntity(fcmToken, legalDongCode));

        regionToken.changeLegalDongCode(legalDongCode);

        regionTokenRepository.save(regionToken);
    }


    /**
     * 인자로 전달한 법정동코드에 속한 fcmToken 리스트를 반환합니다
     * @param legalDongList
     * @return
     */
    public List<String> findFcmTokenByLegalDongList(List<String> legalDongList) {
      return  regionTokenRepository.findByLegalDongCodeIn(legalDongList)
                .stream().map(RegionTokenEntity::getFcmToken).collect(Collectors.toList());
    }


}
