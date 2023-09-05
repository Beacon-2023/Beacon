package com.BEACON.beacon.shelter.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class ShelterResponseDto {

    private String name;

    private Double latitude;

    private Double longitude;

    private String tel;

    private String detailAddress;

    public ShelterResponseDto(String name, Double latitude, Double longitude, String tel,
            String detailAddress) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tel = tel;
        this.detailAddress = detailAddress;
    }
}
