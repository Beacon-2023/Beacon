package com.BEACON.beacon.shelter.dto;

import com.BEACON.beacon.region.domain.Region;
import com.BEACON.beacon.shelter.domain.ShelterCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class ShelterDto {

    private Long id;

    private ShelterCategory shelterCategory;

    private String name;

    private Double latitude;

    private Double longitude;

    private String tel;

    private String detailAddress;

    private Region region;

    public ShelterDto(Long id, ShelterCategory shelterCategory, String name, Double latitude,
            Double longitude, String tel, String detailAddress, Region region) {
        this.id = id;
        this.shelterCategory = shelterCategory;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tel = tel;
        this.detailAddress = detailAddress;
        this.region = region;
    }
}
