package com.BEACON.beacon.domain.scraping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisasterDto {
    private String disasterName;

    private String createdAt;

    private String receivedAreaName;

    private String content;
}
