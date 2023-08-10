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
@AllArgsConstructor
public class DisasterAlert {

    @Id
    @Column(name = "disaster_id")
    private Long id;

    private String disasterName;

    private String createdAt;

    private String receivedAreaName;

    private String content;

}

