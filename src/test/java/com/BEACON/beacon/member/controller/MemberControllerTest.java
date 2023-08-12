package com.BEACON.beacon.member.controller;

import com.BEACON.beacon.member.domain.MemberEntity;
import com.BEACON.beacon.member.dto.MemberDto;
import com.BEACON.beacon.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MemberController.class)
class MemberControllerTest {

    private static final String MEMBER_API_URI = "/api/v1/members";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private MemberDto memberDto;
    private MemberEntity memberEntity;
    private ObjectMapper objectMapper;
    @BeforeEach
    void setup(){
        memberDto = new MemberDto("beacon1234","beacon@naver.com","1Q2w3e4r!@");
        memberEntity = MemberDto.toEntity(memberDto,passwordEncoder);
        objectMapper = new ObjectMapper();
    }



    @Test
    @DisplayName("회원가입 성공시 201 반환")
    @WithMockUser
    void verifiedHttpRequestMatching() throws Exception{
        when(memberService.isDuplicatedId(any())).thenReturn(false);
        when(memberService.registrationMember(any())).thenReturn(1L);

        mockMvc.perform(post(MEMBER_API_URI)
                        .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }





}