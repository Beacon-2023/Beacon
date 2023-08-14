package com.BEACON.beacon.member.service;

import com.BEACON.beacon.member.domain.MemberEntity;
import com.BEACON.beacon.member.dto.SessionDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionLoginService {
    private final MemberService memberService;
    public static final String LOGIN_MEMBER = "loginMember";

    public void login(String userId, HttpServletRequest request){
        MemberEntity member = memberService.findMemberByUserId(userId);

        SessionDto sessionDto = SessionDto.builder()
                .userId(member.getUserId())
                .password(member.getPassword())
                .build();

        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_MEMBER,sessionDto);
    }
}
