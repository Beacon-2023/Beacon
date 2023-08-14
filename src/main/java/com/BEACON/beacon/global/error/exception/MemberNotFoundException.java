package com.BEACON.beacon.global.error.exception;

public class MemberNotFoundException extends RuntimeException{

    public MemberNotFoundException(){
    }

    public MemberNotFoundException(String message) {
        super(message);
    }

}
