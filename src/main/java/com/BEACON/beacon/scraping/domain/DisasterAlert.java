package com.BEACON.beacon.scraping.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
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

    private String disasterName;

    private String createdAt;

    private String receivedAreaName;

    private String content;

    public DisasterAlert(Long id, String disasterName, String createdAt, String receivedAreaName,
            String content) {
        this.id = id;
        this.disasterName = disasterName;
        this.createdAt = createdAt;
        this.receivedAreaName = receivedAreaName;
        this.content = content;
    }
}

