package com.BEACON.beacon.scraping.dto;

import com.BEACON.beacon.scraping.domain.DisasterAlert;
import org.springframework.stereotype.Component;

@Component
public class DisasterAlertMapper {
    public static DisasterAlert toEntity(DisasterAlertDto dto) {
        return DisasterAlert.builder()
                .id(dto.getId())
                .disasterCategory(dto.getDisasterCategory())
                .createdAt(dto.getCreatedAt())
                .receivedAreaName(dto.getReceivedAreaName())
                .content(dto.getContent())
                .build();
    }

    public static DisasterAlertDto toDto(DisasterAlert disasterAlert) {
        return DisasterAlertDto.builder()
                .id(disasterAlert.getId())
                .disasterCategory(disasterAlert.getDisasterCategory())
                .createdAt(disasterAlert.getCreatedAt())
                .receivedAreaName(disasterAlert.getReceivedAreaName())
                .content(disasterAlert.getContent())
                .build();
    }
}
