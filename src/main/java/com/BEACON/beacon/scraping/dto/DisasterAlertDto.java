package com.BEACON.beacon.scraping.dto;

import com.BEACON.beacon.region.domain.Region;
import com.BEACON.beacon.region.domain.RegionAlert;
import com.BEACON.beacon.region.dto.RegionAlertDto;
import com.BEACON.beacon.scraping.domain.DisasterCategory;
import jakarta.persistence.ForeignKey;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class DisasterAlertDto {

    private Long id;

    private DisasterCategory disasterCategory;

    private String createdAt;

    private String content;

    private List<RegionAlertDto> regionAlertDtoList;

    public DisasterAlertDto(Long id, DisasterCategory disasterCategory, String createdAt,
            String content, List<RegionAlertDto> regionAlertDtoList) {
        this.id = id;
        this.disasterCategory = disasterCategory;
        this.createdAt = createdAt;
        this.content = content;
        this.regionAlertDtoList = regionAlertDtoList;
    }
}
