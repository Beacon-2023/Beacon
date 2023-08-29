package com.BEACON.beacon.location.controller;

import com.BEACON.beacon.location.dto.LocationDto;
import com.BEACON.beacon.location.service.LocationApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "WorkManager")
public class LocationController {

    private final LocationApiService locationApiService;

    /**
     * 회원,비회원의 fcm토큰과 위치를 db에 저장
     * @param locationDto
     * 404: 법정동 코드 찾을 수 없음
     * 201: 정상적으로 법정동코드 저장완료
     * @throws IOException
     */
    @Operation(summary = "위치 및 fcm토큰 저장", description = "회원과 비회원 모두의 FCM 토큰과 위치를 workmanager를 통해 저장합니다")
    @ApiResponse(responseCode = "201", description = "법정동코드와 fcm토큰 저장 성공")
    @ApiResponse(responseCode = "404", description = "법정동코드를 찾을 수 없습니다")
    @PostMapping("/location-token")
    public ResponseEntity<HttpStatus> processLocationAndToken(@RequestBody @Valid LocationDto locationDto) throws Exception {
        //해당 위치(위도,경도)에 대한 법정동코드 가져오기
       String legalDongCode = locationApiService.findLegalDongCode( locationDto.getLongitude(),locationDto.getLatitude());

        locationApiService.storeRegionAndFcmToken(locationDto.getFcmToken(),legalDongCode);

        return RESPONSE_CREATED;
    }

}
