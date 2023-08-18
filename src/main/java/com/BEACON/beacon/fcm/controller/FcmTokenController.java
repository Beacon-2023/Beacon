package com.BEACON.beacon.fcm.controller;

import com.BEACON.beacon.fcm.dto.FcmTokenDto;
import com.BEACON.beacon.fcm.service.FcmTokenService;
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
public class FcmTokenController {

    private final FcmTokenService fcmTokenService;


    /**
     * FCM 토큰을 수신받기 위한 메서드
     * @return 200
     */
    @PostMapping("/token")
    public ResponseEntity<HttpStatus> tokenRegister(@RequestBody FcmTokenDto fcmTokenDto){
        fcmTokenService.addFcmToken(fcmTokenDto);

        return RESPONSE_CREATED;
    }
}
