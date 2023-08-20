package com.BEACON.beacon.member.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class SessionDto implements Serializable {
    private String userName;
}
