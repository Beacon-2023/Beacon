package com.BEACON.beacon.location.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;


@Getter
@Schema(description = "위치 정보 및 fcm 토큰 전달 객체")
public class LocationDto {


    @Schema(description = "위도")
    Double latitude;


    @Schema(description = "경도")
    Double longitude;

    @NotEmpty
    @Schema(description = "FCM토큰")
    String fcmToken;

    public LocationDto(){}

}
