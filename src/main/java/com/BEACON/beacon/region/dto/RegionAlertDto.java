package com.BEACON.beacon.region.dto;

import com.BEACON.beacon.region.domain.Region;
import com.BEACON.beacon.scraping.domain.DisasterAlert;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class RegionAlertDto {

    private Long id;

    private Region region;

    private DisasterAlert disasterAlert;

    public RegionAlertDto(Long id, Region region, DisasterAlert disasterAlert) {
        this.id = id;
        this.region = region;
        this.disasterAlert = disasterAlert;
    }
}
