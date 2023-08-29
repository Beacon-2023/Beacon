package com.BEACON.beacon.location.controller;

import com.BEACON.beacon.location.dto.LocationDto;
import com.BEACON.beacon.location.service.LocationApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

import static com.BEACON.beacon.global.HttpStatusResponse.RESPONSE_CREATED;
import static com.BEACON.beacon.global.HttpStatusResponse.RESPONSE_NOTFOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LocationController {

    private final LocationApiService locationApiService;

    /**
     * 회원,비회원의 fcm토큰과 위치를 db에 저장
     * @param locationDto
     * 404: 법정동 코드 찾을 수 없음
     * 201: 정상적으로 법정동코드 저장완료
     * @throws IOException
     */
    @PostMapping("/location-token")
    public ResponseEntity<HttpStatus> processLocationAndToken(@RequestBody @Valid LocationDto locationDto) throws Exception {
        //해당 위치(위도,경도)에 대한 법정동코드 가져오기
       String legalDongCode = locationApiService.findLegalDongCode( locationDto.getLongitude(),locationDto.getLatitude());

        locationApiService.storeRegionAndFcmToken(locationDto.getFcmToken(),legalDongCode);

        return RESPONSE_CREATED;
    }

}
