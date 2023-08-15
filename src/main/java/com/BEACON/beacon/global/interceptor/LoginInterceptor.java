package com.BEACON.beacon.global.interceptor;

import com.BEACON.beacon.global.annotation.LoginRequired;
import com.BEACON.beacon.global.error.exception.UnAuthenticatedAccessException;
import com.BEACON.beacon.member.service.SessionLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    private final SessionLoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception {
        if (handler instanceof HandlerMethod && ((HandlerMethod) handler).hasMethodAnnotation(LoginRequired.class)) {
            Object member = loginService.getLoginMember(request);


            if (member == null) {
                throw new UnAuthenticatedAccessException("인증되지 않은 회원은 접근할 수 없습니다");
            }

        }
        return true;
    }
}
