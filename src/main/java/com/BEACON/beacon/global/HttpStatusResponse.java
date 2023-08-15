package com.BEACON.beacon.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface HttpStatusResponse {
    //200
     ResponseEntity<HttpStatus> RESPONSE_OK = ResponseEntity.status(HttpStatus.OK).build();
    // 201
     ResponseEntity<HttpStatus> RESPONSE_CREATED = ResponseEntity.status(HttpStatus.CREATED).build();
    //403
     ResponseEntity<HttpStatus> RESPONSE_FORBIDDEN = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    //409
     ResponseEntity<HttpStatus> RESPONSE_CONFLICT = ResponseEntity.status(HttpStatus.CONFLICT).build();
    //400
     ResponseEntity<HttpStatus> RESPONSE_BAD_REQUEST = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
}
