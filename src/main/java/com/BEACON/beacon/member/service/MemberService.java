package com.BEACON.beacon.member.service;

import com.BEACON.beacon.global.error.exception.MemberNotFoundException;
import com.BEACON.beacon.member.dao.MemberRepository;
import com.BEACON.beacon.member.domain.MemberEntity;
import com.BEACON.beacon.member.request.MemberLoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long registrationMember(MemberEntity memberEntity){

       MemberEntity member =  memberRepository.save(memberEntity);

       return member.getId();
    }

    /**
     * 회원가입시 아이디 중복 체크를 진행한다.
     *
     * @param userId 중복체크를 진행할 아이디
     * @return true: 중복된 아이디 false : 중복되지 않은 아이디(생성 가능한 아이디)
     */
    public boolean isDuplicatedId(String userId){
        return memberRepository.existsByUserId(userId);
    }

    /**
     * 회원가입시 이메일 중복 체크를 진행한다.
     * @param email : 중복 체크를 진행할 이메일
     * @return true : 중복된 이메일 false: 중복되지 않은 이메일(생성 가능한 이메일)
     */
    public boolean isDuplicatedEmail(String email){
        return memberRepository.existsByEmail(email);
    }


    /**
     * 사용자가 입력한 비밀번호가 유효한지 검사
     * @param memberDto
     * @param passwordEncoder
     * @return 아이디와 비밀번호가 일치하면 true 아니면 false 반환
     */
    public boolean isValidMember(MemberLoginRequestDto memberDto, PasswordEncoder passwordEncoder){
        MemberEntity member = findMemberByUserId(memberDto.getUserId());

        if(passwordEncoder.matches(memberDto.getPassword(),member.getPassword())){
            return true;
        }
        return false;
    }


    /**
     * 사용자가 입력한 아이디가 존재하는 지 검사
     * @param userId
     * @return MemberEntity or Exception
     */
    public MemberEntity findMemberByUserId(String userId){
        return memberRepository.findMemberByUserId(userId).orElseThrow(()->new MemberNotFoundException("가입된 회원이 아닙니다"));
    }


}
