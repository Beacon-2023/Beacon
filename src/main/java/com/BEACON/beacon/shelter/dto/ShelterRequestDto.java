package com.BEACON.beacon.shelter.dto;

import com.BEACON.beacon.shelter.domain.ShelterCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ShelterRequestDto {

    @NotNull
    private Double x;

    @NotNull
    private Double y;

    @NotNull
    private Integer count;

    @NotNull
    private ShelterCategory shelterCategory;

}
