package com.BEACON.beacon.member.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class MemberLoginRequestDto {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;
}
