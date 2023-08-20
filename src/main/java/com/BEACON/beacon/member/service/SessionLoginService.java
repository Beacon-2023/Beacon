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

    public void login(String userName, HttpServletRequest request){
        MemberEntity member = memberService.findMemberByUserName(userName);

        SessionDto sessionDto = SessionDto.builder()
                .userName(member.getUserName())
                .build();

        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_MEMBER,sessionDto);
        //세션 만료시간 무한대
        session.setMaxInactiveInterval(-1);
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.removeAttribute(LOGIN_MEMBER);
        }
    }

    public Object getLoginMember(HttpServletRequest request){
        HttpSession session = request.getSession();

        return session.getAttribute(LOGIN_MEMBER);
    }
}
