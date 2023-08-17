package com.BEACON.beacon.region.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class Region {

    @Id
    @Column(name = "REGION_CODE")
    private String code;

    private String sido;

    private String sigungu;

    private String dong;

    public Region(String code, String sido, String sigungu, String dong) {
        this.code = code;
        this.sido = sido;
        this.sigungu = sigungu;
        this.dong = dong;
    }
}
