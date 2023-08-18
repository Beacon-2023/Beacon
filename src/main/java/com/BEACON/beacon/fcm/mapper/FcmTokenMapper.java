package com.BEACON.beacon.fcm.mapper;

import com.BEACON.beacon.fcm.UserType;
import com.BEACON.beacon.fcm.domain.FcmTokenEntity;
import com.BEACON.beacon.fcm.dto.FcmTokenDto;
import com.BEACON.beacon.member.domain.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class FcmTokenMapper {
    public static FcmTokenEntity toMemberEntity(FcmTokenDto fcmTokenDto, MemberEntity member){
        return FcmTokenEntity.builder()
                .token(fcmTokenDto.getToken())
                .userType(UserType.MEMBER)
                .member(member)
                .build();
    }

    public static FcmTokenEntity toNoNMemberEntity(FcmTokenDto fcmTokenDto){
        return FcmTokenEntity.builder()
                .token(fcmTokenDto.getToken())
                .userType(UserType.NON_MEMBER)
                .build();
    }

}
