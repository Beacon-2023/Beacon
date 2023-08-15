package com.BEACON.beacon.scraping.dto;

import com.BEACON.beacon.scraping.domain.DisasterAlert;
import org.springframework.stereotype.Component;

@Component
public class DisasterAlertMapper {
    public static DisasterAlert toEntity(DisasterAlertDto dto) {
        return DisasterAlert.builder()
                .id(dto.getId())
                .disasterName(dto.getDisasterName())
                .createdAt(dto.getCreatedAt())
                .receivedAreaName(dto.getReceivedAreaName())
                .content(dto.getContent())
                .build();
    }

    public static DisasterAlertDto toDto(DisasterAlert disasterAlert) {
        return DisasterAlertDto.builder()
                .id(disasterAlert.getId())
                .disasterName(disasterAlert.getDisasterName())
                .createdAt(disasterAlert.getCreatedAt())
                .receivedAreaName(disasterAlert.getReceivedAreaName())
                .content(disasterAlert.getContent())
                .build();
    }
}
