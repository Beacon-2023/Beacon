package com.BEACON.beacon.scraping.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DuplicatedAlertException extends RuntimeException {
    DuplicatedAlertException(String message) {
        super(message);
    }
}
