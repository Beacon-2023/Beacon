package com.BEACON.beacon.guideline.domain;

import com.BEACON.beacon.member.domain.MemberEntity;
import com.BEACON.beacon.scraping.domain.DisasterCategory;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Entity
@Table(name = "CUSTOM_GUIDELINE")
@Getter
public class CustomGuideLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CUSTOM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private MemberEntity memberEntity;

    private String title;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private DisasterCategory disaster;

    public CustomGuideLineEntity(){
    }

    @Builder
    public CustomGuideLineEntity(MemberEntity memberEntity, String title, String content, DisasterCategory disaster) {
        this.memberEntity = memberEntity;
        this.title = title;
        this.content = content;
        this.disaster = disaster;
    }

    public void setMember(MemberEntity memberEntity){
        this.memberEntity = memberEntity;
        memberEntity.addCustomGuideLineEntityList(this);
    }

    public void updateCustomGuide(String title,String content){
        this.title = title;
        this.content = content;
    }
}
