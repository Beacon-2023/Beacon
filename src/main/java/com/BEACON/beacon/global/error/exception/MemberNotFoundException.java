package com.BEACON.beacon.global.error.exception;

import com.BEACON.beacon.fcm.service.FcmTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemberNotFoundException extends RuntimeException{

    private final Logger logger = LoggerFactory.getLogger(FcmTokenService.class);
    public MemberNotFoundException(){
    }

    public MemberNotFoundException(String message) {
        super(message);
        logger.warn("회원을 찾을 수 없습니다");
    }

}
