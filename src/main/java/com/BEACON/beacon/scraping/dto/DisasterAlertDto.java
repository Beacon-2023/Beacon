package com.BEACON.beacon.scraping.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class DisasterAlertDto {

    private Long id;

    private String disasterName;

    private String createdAt;

    private String receivedAreaName;

    private String content;

    public DisasterAlertDto(Long id, String disasterName, String createdAt, String receivedAreaName,
            String content) {
        this.id = id;
        this.disasterName = disasterName;
        this.createdAt = createdAt;
        this.receivedAreaName = receivedAreaName;
        this.content = content;
    }
}
