package com.BEACON.beacon.domain.scraping.dto;

import com.BEACON.beacon.domain.scraping.domain.Disaster;
import org.springframework.stereotype.Component;

@Component
public class DisasterMapper {
    public static Disaster toEntity(DisasterDto dto) {
        return Disaster.builder()
                .disasterName(dto.getDisasterName())
                .createdAt(dto.getCreatedAt())
                .receivedAreaName(dto.getReceivedAreaName())
                .content(dto.getContent())
                .build();
    }

    public static DisasterDto toDto(Disaster disaster) {
        return DisasterDto.builder()
                .disasterName(disaster.getDisasterName())
                .createdAt(disaster.getCreatedAt())
                .receivedAreaName(disaster.getReceivedAreaName())
                .content(disaster.getContent())
                .build();
    }
}
