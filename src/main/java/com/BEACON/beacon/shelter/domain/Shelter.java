package com.BEACON.beacon.shelter.domain;

import com.BEACON.beacon.region.domain.Region;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class Shelter {

    @Id
    @Column(name = "SHELTER_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ShelterCategory shelterCategory;

    private String name;

    private Double latitude;

    private Double longitude;

    private String tel;

    private String detailAddress;

    private Integer regionCode;

    public Shelter(Long id, ShelterCategory shelterCategory, String name, Double latitude,
            Double longitude, String tel, String detailAddress, Integer regionCode) {
        this.id = id;
        this.shelterCategory = shelterCategory;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tel = tel;
        this.detailAddress = detailAddress;
        this.regionCode = regionCode;
    }
}
