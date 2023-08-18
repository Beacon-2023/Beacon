package com.BEACON.beacon.scraping.dto;

import com.BEACON.beacon.region.domain.RegionAlert;
import com.BEACON.beacon.region.dto.RegionAlertDto;
import com.BEACON.beacon.region.dto.RegionMapper;
import com.BEACON.beacon.scraping.domain.DisasterAlert;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class DisasterAlertMapper {
    public static DisasterAlert toEntity(DisasterAlertDto dto) {
        List<RegionAlert> regionAlertList = dto.getRegionAlertDtoList().stream()
                .map(RegionMapper::toEntity)
                .toList();

        return DisasterAlert.builder()
                .id(dto.getId())
                .disasterCategory(dto.getDisasterCategory())
                .createdAt(dto.getCreatedAt())
                .content(dto.getContent())
                .regionAlertList(regionAlertList)
                .build();
    }

    public static DisasterAlertDto toDto(DisasterAlert disasterAlert) {
        List<RegionAlertDto> regionAlertDtoList = disasterAlert.getRegionAlertList().stream()
                .map(RegionMapper::toDto)
                .toList();

        return DisasterAlertDto.builder()
                .id(disasterAlert.getId())
                .disasterCategory(disasterAlert.getDisasterCategory())
                .createdAt(disasterAlert.getCreatedAt())
                .content(disasterAlert.getContent())
                .regionAlertDtoList(regionAlertDtoList)
                .build();
    }
}
