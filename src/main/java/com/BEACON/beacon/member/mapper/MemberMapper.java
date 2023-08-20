package com.BEACON.beacon.member.mapper;

import com.BEACON.beacon.member.MemberStatus;
import com.BEACON.beacon.member.domain.MemberEntity;
import com.BEACON.beacon.member.dto.MemberDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public static MemberEntity toEntity(MemberDto memberDto, PasswordEncoder passwordEncoder) {
        return MemberEntity.builder()
                .userName(memberDto.getUserName())
                .email(memberDto.getEmail())
                .status(MemberStatus.ACTIVE)
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .build();
    }
}
