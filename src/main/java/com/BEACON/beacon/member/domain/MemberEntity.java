package com.BEACON.beacon.member.domain;

import com.BEACON.beacon.global.BaseTimeEntity;
import com.BEACON.beacon.member.MemberStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name="MEMBER")
public class MemberEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String userName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    public MemberEntity(){
    }
    @Builder
    public MemberEntity(String userName, String email, String password, MemberStatus status) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.status = status;
    }




}
