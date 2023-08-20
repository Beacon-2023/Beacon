package com.BEACON.beacon.member.service;

import com.BEACON.beacon.global.error.exception.MemberNotFoundException;
import com.BEACON.beacon.member.dao.MemberRepository;
import com.BEACON.beacon.member.domain.MemberEntity;
import com.BEACON.beacon.member.dto.MemberDto;
import com.BEACON.beacon.member.mapper.MemberMapper;
import com.BEACON.beacon.member.request.MemberLoginRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public Long registrationMember(MemberDto memberDto){

        MemberEntity memberEntity = memberMapper.toEntity(memberDto,passwordEncoder);

        MemberEntity member =  memberRepository.save(memberEntity);

       return member.getId();
    }

    /**
     * 회원가입시 아이디 중복 체크를 진행한다.
     *
     * @param userName 중복체크를 진행할 아이디
     * @return true: 중복된 아이디 false : 중복되지 않은 아이디(생성 가능한 아이디)
     */
    public boolean isDuplicatedId(String userName){
        return memberRepository.existsByUserName(userName);
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
     * @return 아이디와 비밀번호가 일치하면 true 아니면 false 반환
     */
    public boolean isValidMember(MemberLoginRequestDto memberDto){
        MemberEntity member = findMemberByUserName(memberDto.getUserName());

        if(passwordEncoder.matches(memberDto.getPassword(),member.getPassword())){
            return true;
        }
        return false;
    }


    /**
     * 사용자가 입력한 아이디가 존재하는 지 검사
     * @param userName
     * @return MemberEntity or Exception
     */
    public MemberEntity findMemberByUserName(String userName){
        return memberRepository.findMemberByUserName(userName).orElseThrow(()->new MemberNotFoundException("가입된 회원이 아닙니다"));
    }


}
