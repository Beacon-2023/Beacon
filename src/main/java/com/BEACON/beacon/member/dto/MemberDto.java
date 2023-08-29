package com.BEACON.beacon.member.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
@Schema(description = "회원 정보 전달 객체")
public class MemberDto {

    @NotEmpty
    @Schema(description = "회원의 아이디", example = "beacon1234")
    private String userName;

    @NotEmpty
    @Email(message = "유효하지 않은 이메일 형식입니다.",
            regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    @Schema(description = "회원의 이메일", example = "beacon@naver.com")
    private String email;

    @NotEmpty
    @Pattern(message = "최소 한개 이상의 대소문자와 숫자, 특수문자를 포함한 8자 이상 16자 이하의 비밀번호를 입력해야 합니다.",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!~$%^&-+=()])(?=\\S+$).{8,16}$")
    @Schema(description = "회원의 비밀번호", example="1Q2w3e4r!@")
    private String password;

    private MemberDto(){
    }

    @Builder
    public MemberDto(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }



}
