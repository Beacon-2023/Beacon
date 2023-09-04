package com.BEACON.beacon.guideline.domain;

import com.BEACON.beacon.guideline.response.BasicGuideLineResponse;
import com.BEACON.beacon.scraping.domain.DisasterCategory;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="BASIC_GUIDLINE")
@Getter
public class BasicGuideLineEntity {
    @Id
    @Column(name="GUIDE_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private DisasterCategory disaster;

    private String title;

    @Lob
    private String content;


    public BasicGuideLineEntity(){
    }




}
