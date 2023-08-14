package com.BEACON.beacon.global.error.exception;

public class UnAuthenticatedAccessException extends RuntimeException{
    public UnAuthenticatedAccessException(){
    }

    public UnAuthenticatedAccessException(String message){
        super(message);
    }
}
