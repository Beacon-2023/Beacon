package com.BEACON.beacon.scraping.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
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

    private String receivedAreaName;

    private String content;

    public DisasterAlert(Long id, DisasterCategory disasterCategory, String createdAt,
            String receivedAreaName, String content) {
        this.id = id;
        this.disasterCategory = disasterCategory;
        this.createdAt = createdAt;
        this.receivedAreaName = receivedAreaName;
        this.content = content;
    }
}

