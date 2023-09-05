package com.BEACON.beacon.guideline.mapper;

import com.BEACON.beacon.guideline.domain.CustomGuideLineEntity;
import com.BEACON.beacon.guideline.dto.CustomGuideLineDto;
import com.BEACON.beacon.guideline.response.CustomGuideLineResponse;
import com.BEACON.beacon.member.domain.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class GuideLineMapper {
    public static CustomGuideLineEntity toCustomGuideLineEntity(CustomGuideLineDto customGuideLineDto, MemberEntity memberEntity){

        return CustomGuideLineEntity.builder()
                .title(customGuideLineDto.getTitle())
                .content(customGuideLineDto.getContent())
                .disaster(customGuideLineDto.getDisaster())
                .build();
    }

    public static CustomGuideLineResponse toCustomGuideLineResponse(CustomGuideLineEntity customGuideLineEntity){
        return CustomGuideLineResponse.builder()
                .title(customGuideLineEntity.getTitle())
                .content(customGuideLineEntity.getContent())
                .disaster(String.valueOf(customGuideLineEntity.getDisaster()))
                .build();
    }
}
