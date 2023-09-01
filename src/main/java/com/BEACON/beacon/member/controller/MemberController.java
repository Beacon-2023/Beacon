package com.BEACON.beacon.member.controller;

import com.BEACON.beacon.global.annotation.LoginRequired;
import com.BEACON.beacon.member.dto.MemberDto;
import com.BEACON.beacon.member.request.MemberLoginRequestDto;
import com.BEACON.beacon.member.service.MemberService;
import com.BEACON.beacon.member.service.SessionLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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
@Tag(name="Member")
public class MemberController {

    private final MemberService memberService;
    private final SessionLoginService sessionLoginService;

    @Operation(summary = "회원가입", description = "제공된 회원정보를 등록합니다")
    @ApiResponse(responseCode = "201", description = "등록 성공")
    @ApiResponse(responseCode = "403", description = "중복되는 아이디 존재")
    @PostMapping
    public ResponseEntity<HttpStatus> signUp(@RequestBody @Valid MemberDto memberDto) {

        //고객이 등록한 아이디 중복 체크
        boolean isDuplicatedId = memberService.isDuplicatedId(memberDto.getUserName());
        if(isDuplicatedId){
            return RESPONSE_FORBIDDEN;
        }

        memberService.registrationMember(memberDto);

        return RESPONSE_CREATED;
    }


    @Operation(summary = "가입 시 이메일 중복 여부 확인")
    @ApiResponse(responseCode = "200", description = "사용가능한 이메일")
    @ApiResponse(responseCode = "409", description = "이미 사용 중인 이메일")
    @GetMapping("/duplicated/{email}")
    public ResponseEntity<HttpStatus> isDuplicatedEmail(@PathVariable String email) {
        boolean isDuplicatedEmail = memberService.isDuplicatedEmail(email);

        if (isDuplicatedEmail) {
            return RESPONSE_CONFLICT;
        }
        return RESPONSE_OK;
    }


    @Operation(summary = "회원 로그인", description = "제공된 회원 정보로 로그인")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    @ApiResponse(responseCode = "400", description = "유효하지 않은 아이디/비밀번호")
    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody @Valid MemberLoginRequestDto memberDto, HttpServletRequest request){

        boolean isValidMember = memberService.isValidMember(memberDto);

        if(isValidMember){
            sessionLoginService.login(memberDto.getUserName(),request);
            return RESPONSE_OK;
        }
        return RESPONSE_BAD_REQUEST;
    }


    /**
     * 로그아웃 처리
     * @param request
     * @return 200 : 로그아웃 성공
     */
    @Operation(summary = "로그아웃")
    @ApiResponse(responseCode = "200", description = "로그아웃 성공")
    @LoginRequired
    @GetMapping("/logout")
    public ResponseEntity<HttpStatus> logout(HttpServletRequest request) {
        sessionLoginService.logout(request);
        return RESPONSE_OK;
    }

}


