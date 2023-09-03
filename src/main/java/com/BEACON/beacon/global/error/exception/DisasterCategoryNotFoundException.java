package com.BEACON.beacon.global.error.exception;

import com.BEACON.beacon.fcm.service.FcmTokenService;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DisasterCategoryNotFoundException extends RuntimeException{
    private final Logger logger = LoggerFactory.getLogger(FcmTokenService.class);

    public DisasterCategoryNotFoundException(){
    }

    public DisasterCategoryNotFoundException(String msg){
        super(msg);
    }
}
