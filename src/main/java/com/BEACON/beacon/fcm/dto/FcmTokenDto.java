package com.BEACON.beacon.fcm.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class FcmTokenDto {
    //토큰 , 회원 아이디
    @NotEmpty
    private String token;

    private String username;



}
