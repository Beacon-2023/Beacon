package com.BEACON.beacon.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Schema(description = "회원 로그인 요청 정보")
public class MemberLoginRequestDto {
    @NotEmpty
    @Schema(description = "회원의 아이디", example = "beacon1234")
    private String userName;

    @NotEmpty
    @Schema(description = "회원의 비밀번호", example="1Q2w3e4r!@")
    private String password;
}
