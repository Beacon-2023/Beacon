package com.BEACON.beacon.location.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class LocationDto {

    @NotEmpty
    Double latitude;
    @NotEmpty
    Double longitude;
    @NotEmpty
    String fcmToken;

    public LocationDto(){}

}
