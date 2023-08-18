package com.BEACON.beacon.fcm.service;

import com.BEACON.beacon.fcm.dao.FcmTokenRepository;
import com.BEACON.beacon.fcm.domain.FcmTokenEntity;
import com.BEACON.beacon.fcm.dto.FcmTokenDto;
import com.BEACON.beacon.fcm.mapper.FcmTokenMapper;
import com.BEACON.beacon.member.domain.MemberEntity;
import com.BEACON.beacon.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FcmTokenService {
    private final MemberService memberService;
    private final FcmTokenRepository fcmTokenRepository;
    private final FcmTokenMapper fcmTokenMapper;

    public void addFcmToken(FcmTokenDto fcmTokenDto){
        MemberEntity member;
        FcmTokenEntity fcmTokenEntity;

        if(fcmTokenDto.getUsername()!=null){
          member =  memberService.findMemberByUserId(fcmTokenDto.getUsername());
          fcmTokenEntity =  fcmTokenMapper.toMemberEntity(fcmTokenDto,member);
        }else{
           fcmTokenEntity =  fcmTokenMapper.toNoNMemberEntity(fcmTokenDto);
        }
        fcmTokenRepository.save(fcmTokenEntity);
    }




}
