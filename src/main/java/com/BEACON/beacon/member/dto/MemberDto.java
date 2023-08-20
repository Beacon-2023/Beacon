package com.BEACON.beacon.member.dto;

import com.BEACON.beacon.member.MemberStatus;
import com.BEACON.beacon.member.domain.MemberEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter @ToString
public class MemberDto {

    @NotEmpty
    private String userName;

    @NotEmpty
    @Email(message = "유효하지 않은 이메일 형식입니다.",
            regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    @NotEmpty
    @Pattern(message = "최소 한개 이상의 대소문자와 숫자, 특수문자를 포함한 8자 이상 16자 이하의 비밀번호를 입력해야 합니다.",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!~$%^&-+=()])(?=\\S+$).{8,16}$")
    private String password;

    private MemberDto(){
    }

    @Builder
    public MemberDto(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public static MemberEntity toEntity(MemberDto memberDto, PasswordEncoder passwordEncoder) {
        return MemberEntity.builder()
                .userName(memberDto.getUserName())
                .email(memberDto.getEmail())
                .status(MemberStatus.ACTIVE)
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .build();
    }

}
