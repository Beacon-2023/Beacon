package com.BEACON.beacon.location.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name="REGION_TOKEN")
@Getter
public class RegionTokenEntity {
    @Id
    @Column(name="FCM_TOKEN",unique = true)
    private String fcmToken;

    @Column(name="LEGAL_DONG_CODE")
    private String legalDongCode;

    public RegionTokenEntity(){
    }
    @Builder
    public RegionTokenEntity(String fcmToken, String legalDongCode) {
        this.fcmToken = fcmToken;
        this.legalDongCode = legalDongCode;
    }

    public void changeLegalDongCode(String legalDongCode){
        this.legalDongCode = legalDongCode;
    }

}
