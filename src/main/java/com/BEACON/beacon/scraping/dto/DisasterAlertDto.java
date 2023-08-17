package com.BEACON.beacon.scraping.dto;

import com.BEACON.beacon.scraping.domain.DisasterCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class DisasterAlertDto {

    @NotNull
    private Long id;

    @NotNull
    private DisasterCategory disasterCategory;

    @NotNull
    private String createdAt;

    @NotNull
    private String receivedAreaName;

    @NotNull
    private String content;

    public DisasterAlertDto(Long id, DisasterCategory disasterCategory, String createdAt,
            String receivedAreaName, String content) {
        this.id = id;
        this.disasterCategory = disasterCategory;
        this.createdAt = createdAt;
        this.receivedAreaName = receivedAreaName;
        this.content = content;
    }
}
