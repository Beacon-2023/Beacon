package com.BEACON.beacon.member.integration;

import com.BEACON.beacon.member.dao.MemberRepository;
import com.BEACON.beacon.member.domain.MemberEntity;
import com.BEACON.beacon.member.dto.MemberDto;
import com.BEACON.beacon.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MemberServiceIntegrationTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private MemberDto memberDto;
    private MemberEntity memberEntity;

    @BeforeEach
    void setup(){
        memberDto = MemberDto.builder()
                .userId("beacon1234")
                .email("beacon@admin.com")
                .password("1q2w3e4r!")
                .build();

        memberEntity = MemberDto.toEntity(memberDto,passwordEncoder);
    }

    @Test
    @DisplayName("회원이 정상적으로 저장됐는지 테스트")
    @Transactional
    @Rollback(false)
    public void saveMember(){
        //when
        Long saveId = memberService.registrationMember(memberEntity);

        //then
        Optional<MemberEntity> findMemberOptional = memberRepository.findById(saveId);

        assertTrue(findMemberOptional.isPresent(), "회원이 정상적으로 저장되지 않았습니다.");

        MemberEntity findMember = findMemberOptional.get();

        assertEquals("beacon1234",findMember.getUserId());
        assertEquals("beacon@admin.com",findMember.getEmail());




    }

}
