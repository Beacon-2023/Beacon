package com.BEACON.beacon.region.exception;

public class NoSuchRegionAlertException extends RuntimeException {

    public NoSuchRegionAlertException() {
    }

    public NoSuchRegionAlertException(String message) {
        super(message);
    }
}
