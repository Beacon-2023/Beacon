package com.BEACON.beacon.fcm.controller;

import com.BEACON.beacon.fcm.dto.FcmTokenDto;
import com.BEACON.beacon.fcm.service.FcmTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.BEACON.beacon.global.HttpStatusResponse.RESPONSE_CREATED;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "FCM")
public class FcmTokenController {

    private final FcmTokenService fcmTokenService;



    @Operation(summary = "FCM 토큰 등록", description = "FCM 토큰을 서버에 등록합니다. 회원의 fcm토큰이면 회원아이디도 같이 보냅니다")
    @ApiResponse(responseCode = "201" ,description = "등록 성공")
    @PostMapping("/token")
    public ResponseEntity<HttpStatus> tokenRegister(
            @RequestBody
            @Parameter(description="FCM 토큰 정보", required=true)
            FcmTokenDto fcmTokenDto){

        fcmTokenService.addFcmToken(fcmTokenDto);

        return RESPONSE_CREATED;
    }
}
