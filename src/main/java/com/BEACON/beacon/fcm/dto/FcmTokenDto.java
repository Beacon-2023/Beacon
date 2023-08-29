package com.BEACON.beacon.fcm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
@Schema(description = "FCM 토큰 정보 전달 객체")
public class FcmTokenDto {
    //토큰 , 회원 아이디
    @NotEmpty
    @Schema(description = "FCM 토큰")
    private String token;

    @Schema(description = "회원의 아이디", example = "beacon1234")
    private String userName;


}
