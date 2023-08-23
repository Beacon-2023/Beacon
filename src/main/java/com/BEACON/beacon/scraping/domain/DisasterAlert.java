package com.BEACON.beacon.scraping.domain;

import com.BEACON.beacon.region.domain.RegionAlert;
import com.BEACON.beacon.region.dto.RegionAlertDto;
import com.BEACON.beacon.region.mapper.RegionMapper;
import com.BEACON.beacon.scraping.mapper.DisasterAlertMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class DisasterAlert {

    @Id
    @Column(name = "DISASTER_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private DisasterCategory disasterCategory;

    private String createdAt;

    private String content;

    @OneToMany(mappedBy = "disasterAlert")
    private List<RegionAlert> regionAlertList;

    public DisasterAlert(Long id, DisasterCategory disasterCategory, String createdAt,
            String content, List<RegionAlert> regionAlertList) {
        this.id = id;
        this.disasterCategory = disasterCategory;
        this.createdAt = createdAt;
        this.content = content;
        this.regionAlertList = regionAlertList;
    }

    public void addRegionAlert(RegionAlertDto dto) {
        RegionAlert entity = RegionMapper.toEntity(dto);
        this.regionAlertList.add(entity);
        if (entity.getDisasterAlert() != this) {
            entity.setDisasterAlert(DisasterAlertMapper.toDto(this));
        }
    }
}

