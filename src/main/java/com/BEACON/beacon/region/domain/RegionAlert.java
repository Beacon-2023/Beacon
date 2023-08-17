package com.BEACON.beacon.region.domain;

import com.BEACON.beacon.scraping.domain.DisasterAlert;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class RegionAlert {

    @Id @GeneratedValue
    @Column(name = "REGION_ALERT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "REGION_CODE")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "DISASTER_ID")
    private DisasterAlert disasterAlert;

    public RegionAlert(Long id, Region region, DisasterAlert disasterAlert) {
        this.id = id;
        this.region = region;
        this.disasterAlert = disasterAlert;
    }
}
