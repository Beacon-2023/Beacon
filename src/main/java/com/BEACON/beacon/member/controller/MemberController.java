package com.BEACON.beacon.member.controller;

import com.BEACON.beacon.member.domain.MemberEntity;
import com.BEACON.beacon.member.dto.MemberDto;
import com.BEACON.beacon.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.BEACON.beacon.global.HttpStatusResponse.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;


    /**
     * 고객이 입력한 정보로 회원가입을 진행한다.
     * 201: 회원가입 성공
     * 403: 아이디 중복
     */
    @PostMapping
    public ResponseEntity<HttpStatus> signUp(@RequestBody @Valid MemberDto memberDto) {

        //고객이 등록한 아이디 중복 체크
        boolean isDuplicatedId = memberService.isDuplicatedId(memberDto.getUserId());
        if(isDuplicatedId){
            return RESPONSE_FORBIDDEN;
        }

        MemberEntity memberEntity = MemberDto.toEntity(memberDto,passwordEncoder);
        memberService.registrationMember(memberEntity);

        return RESPONSE_CREATED;
    }

    /**
     * 회원가입 시 이메일의 중복체크를 진행한다.
     * 200: 이메일 사용가능
     * 409: 이메일 중복
     */
    @GetMapping("/duplicated/{email}")
    public ResponseEntity<HttpStatus> isDuplicatedEmail(@PathVariable String email) {
        boolean isDuplicatedEmail = memberService.isDuplicatedEmail(email);

        if (isDuplicatedEmail) {
            return RESPONSE_CONFLICT;
        }
        return RESPONSE_OK;
    }


}


