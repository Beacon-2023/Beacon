package com.BEACON.beacon.member.service;

import com.BEACON.beacon.member.dao.MemberRepository;
import com.BEACON.beacon.member.domain.MemberEntity;
import com.BEACON.beacon.member.dto.MemberDto;
import com.BEACON.beacon.member.mapper.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberMapper memberMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    private MemberDto memberDto;

    private MemberEntity memberEntity;

    @BeforeEach
    void setup(){
        when(passwordEncoder.encode(any())).thenReturn("1q2w3e4r!");
        memberDto = MemberDto.builder()
                .userName("beacon1234")
                .email("beacon@admin.com")
                .password("1q2w3e4r!")
                .build();

        memberEntity = memberMapper.toEntity(memberDto,passwordEncoder);
    }

    @Test
    @DisplayName("중복된 회원아이디가 존재하지 않은 경우 FALSE를 반환한다.")
    void isNotDuplicatedMemberId(){
        when(memberRepository.existsByUserName(any())).thenReturn(false);

        assertFalse(memberService.isDuplicatedId(memberEntity.getUserName()));
    }


    @Test
    @DisplayName("중복된 회원아이디가 존재하는 경우 TRUE를 반환한다.")
    void isDuplicatedMemberId(){
        when(memberRepository.existsByUserName(any())).thenReturn(true);

        assertTrue(memberService.isDuplicatedId(memberEntity.getUserName()));
    }

    @Test
    @DisplayName("중복된 이메일이 존재하지 않은 경우 FALSE를 반환한다.")
    void isNotDuplicatedEmailExist(){
        when(memberRepository.existsByEmail(any())).thenReturn(false);

        assertFalse(memberService.isDuplicatedEmail(memberEntity.getEmail()));
    }
    @Test
    @DisplayName("중복된 이메일이 존재하는 경우 TRUE를 반환한다.")
    void isDuplicatedEmailExist(){
        when(memberRepository.existsByEmail(any())).thenReturn(true);

        assertTrue(memberService.isDuplicatedEmail(memberEntity.getEmail()));
    }




}