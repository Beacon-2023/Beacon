package com.BEACON.beacon.member.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SessionLoginServiceTest {

    private static final String LOGIN_MEMBER = "loginMember";
    private MockHttpServletRequest request;
    private MockHttpSession session;
    private SessionLoginService sessionLoginService;


    @Mock
    private MemberService memberService;



    @BeforeEach
    void setUp() {
        sessionLoginService = new SessionLoginService(memberService);
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        request.setSession(session);
    }

    @Test
    @DisplayName("로그인한 세션이 없을 때 null 값 반환하는 지 테스트")
    void getLoginMemberIsNull(){
      Object member =   session.getAttribute(LOGIN_MEMBER);
      assertEquals(null,member,"member에는 아무것도 없어야 한다");
    }

    @Test
    @DisplayName("로그인한 세션이 있을 때 Object가 반환되는지 테스트")
    void getLoginMemberIsNotNull(){

    }
}