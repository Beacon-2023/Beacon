package com.BEACON.beacon.scraping.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisasterAlertDto {

    private Long id;

    private String disasterName;

    private String createdAt;

    private String receivedAreaName;

    private String content;

}
