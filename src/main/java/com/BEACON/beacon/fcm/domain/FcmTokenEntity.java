package com.BEACON.beacon.fcm.domain;

import com.BEACON.beacon.fcm.UserType;
import com.BEACON.beacon.member.domain.MemberEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "FCMTOKEN")
public class FcmTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="TOKEN" , unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "USERTYPE")
    private UserType userType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private MemberEntity member;

    public FcmTokenEntity(){
    }
    @Builder
    public FcmTokenEntity(String token, UserType userType, MemberEntity member) {
        this.token = token;
        this.userType = userType;
        this.member = member;
    }
}
