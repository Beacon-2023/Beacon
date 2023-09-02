package com.BEACON.beacon.fcm.service;

import com.BEACON.beacon.fcm.dao.FcmTokenRepository;
import com.BEACON.beacon.fcm.domain.FcmTokenEntity;
import com.BEACON.beacon.fcm.dto.FcmTokenDto;
import com.BEACON.beacon.fcm.mapper.FcmTokenMapper;
import com.BEACON.beacon.location.service.LocationApiService;
import com.BEACON.beacon.member.domain.MemberEntity;
import com.BEACON.beacon.member.service.MemberService;
import com.BEACON.beacon.region.domain.Region;
import com.BEACON.beacon.region.dto.RegionAlertDto;
import com.BEACON.beacon.scraping.dto.DisasterAlertDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FcmTokenService {
    private Logger logger = LoggerFactory.getLogger(FcmTokenService.class);
    private final MemberService memberService;
    private final FcmTokenRepository fcmTokenRepository;
    private final FcmTokenMapper fcmTokenMapper;
    private final LocationApiService locationApiService;
    private final FirebaseCloudMessageService firebaseCloudMessageService;

    @Transactional
    public void addFcmToken(FcmTokenDto fcmTokenDto){
        MemberEntity member;
        FcmTokenEntity fcmTokenEntity;

        if(!fcmTokenDto.getUserName().isEmpty()){
          member =  memberService.findMemberByUserName(fcmTokenDto.getUserName());
          fcmTokenEntity =  fcmTokenMapper.toMemberEntity(fcmTokenDto,member);
        }else{
           fcmTokenEntity =  fcmTokenMapper.toNoNMemberEntity(fcmTokenDto);
        }
        fcmTokenRepository.save(fcmTokenEntity);
    }

    /**
     * 스크래핑 기능에서 새로운 재난 문자가 들어오면 해당 메소드를 호출
     * @param disasterAlertDto
     */
    public void sendDisasterPushMessage(DisasterAlertDto disasterAlertDto){

        // 1. 스크래핑한 새로운 재난 문자들 지역 코드 출력
        List<String> legalDongCodeList = disasterAlertDto.getRegionAlertDtoList().stream()
                .map(RegionAlertDto::getRegion)
                .map(Region::getLegalDongCode)
                .toList();

        List<String> fcmTokenList = locationApiService.findFcmTokenByLegalDongList(legalDongCodeList);

        for (String token : fcmTokenList) {
            try {
                String FCMTitle = "재난문자";
                firebaseCloudMessageService.sendMessageTo(token, FCMTitle,disasterAlertDto.getContent());
            } catch (IOException e) {
                String detailMessage = String.format("IOException: 재난문자를 %s 토큰에 보내는 것을 실패하였습니다.",token);
                logger.warn(detailMessage);
                throw new RuntimeException(e);
            }
        }

    }





}
