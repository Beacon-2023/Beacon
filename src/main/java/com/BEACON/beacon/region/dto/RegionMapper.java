package com.BEACON.beacon.region.dto;

import com.BEACON.beacon.region.domain.Region;
import com.BEACON.beacon.region.domain.RegionAlert;
import org.springframework.stereotype.Component;

@Component
public class RegionMapper {
    public static Region toEntity(RegionDto dto) {
        return Region.builder()
                .code(dto.getCode())
                .sido(dto.getSido())
                .sigungu(dto.getSigungu())
                .dong(dto.getDong())
                .build();
    }

    public static RegionDto toDto(Region region) {
        return RegionDto.builder()
                .code(region.getCode())
                .sido(region.getSido())
                .sigungu(region.getSigungu())
                .dong(region.getDong())
                .build();
    }

    public static RegionAlert toEntity(RegionAlertDto dto) {
        return RegionAlert.builder()
                .id(dto.getId())
                .region(dto.getRegion())
                .disasterAlert(dto.getDisasterAlert())
                .build();
    }

    public static RegionAlertDto toDto(RegionAlert regionAlert) {
        return RegionAlertDto.builder()
                .id(regionAlert.getId())
                .region(regionAlert.getRegion())
                .disasterAlert(regionAlert.getDisasterAlert())
                .build();
    }

}
