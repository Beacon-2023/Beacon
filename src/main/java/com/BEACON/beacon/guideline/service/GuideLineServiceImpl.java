package com.BEACON.beacon.guideline.service;

import com.BEACON.beacon.fcm.service.FcmTokenService;
import com.BEACON.beacon.global.error.exception.DisasterCategoryNotFoundException;
import com.BEACON.beacon.global.error.exception.UnAuthenticatedAccessException;
import com.BEACON.beacon.guideline.dao.BasicGuideLineRepository;
import com.BEACON.beacon.guideline.dao.CustomGuideLineRepository;
import com.BEACON.beacon.guideline.domain.BasicGuideLineEntity;
import com.BEACON.beacon.guideline.domain.CustomGuideLineEntity;
import com.BEACON.beacon.guideline.dto.CustomGuideLineDto;
import com.BEACON.beacon.guideline.mapper.GuideLineMapper;
import com.BEACON.beacon.guideline.response.BasicGuideLineResponse;
import com.BEACON.beacon.guideline.response.CustomGuideLineResponse;
import com.BEACON.beacon.member.domain.MemberEntity;
import com.BEACON.beacon.member.service.MemberService;
import com.BEACON.beacon.member.service.SessionLoginService;
import com.BEACON.beacon.scraping.domain.DisasterCategory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuideLineServiceImpl implements GuideLineService {
    private final Logger logger = LoggerFactory.getLogger(FcmTokenService.class);
    private final BasicGuideLineRepository basicGuideLineRepository;
    private final CustomGuideLineRepository customGuideLineRepository;
    private final MemberService memberService;
    private final SessionLoginService sessionLoginService;

    /**
     * 재난 카테고리에 맞는 기본 가이드라인 호출
     *
     * @param disasterCategory
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public BasicGuideLineResponse getBasicGuideLine(DisasterCategory disasterCategory) {
        BasicGuideLineEntity basicGuideLineEntity = basicGuideLineRepository.findOptionalByDisaster(disasterCategory)
                .orElseThrow(DisasterCategoryNotFoundException::new);

        return BasicGuideLineResponse.builder()
                .title(basicGuideLineEntity.getTitle())
                .content(basicGuideLineEntity.getContent())
                .build();
    }

    /**
     * 커스텀가이드라인 등록
     *
     * @param customGuideLineDto
     */
    @Override
    @Transactional
    public void registerCustomGuideLine(CustomGuideLineDto customGuideLineDto, HttpServletRequest request) {
        String username = sessionLoginService.getUserName(request);
        MemberEntity memberEntity = memberService.findMemberByUserName(username);

        //db에 저장
        CustomGuideLineEntity customGuideLineEntity = GuideLineMapper.toCustomGuideLineEntity(customGuideLineDto, memberEntity);
        customGuideLineEntity.setMember(memberEntity);

        customGuideLineRepository.save(customGuideLineEntity);
    }

    /**
     * 회원이 등록한 모든 재난의 커스텀 가이드라인 호출
     *
     * @param
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<CustomGuideLineResponse> getAllCustomGuideLine(HttpServletRequest request) {
        String username = sessionLoginService.getUserName(request);
        MemberEntity memberEntity = memberService.findMemberByUserName(username);

        List<CustomGuideLineResponse> list = memberEntity.getCustomGuideLineEntityList().stream()
                .map(GuideLineMapper::toCustomGuideLineResponse).toList();

        logger.info("멤버가 가지고 있는 커스텀 재난가이드라인 가져오기 성공");

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public CustomGuideLineResponse getCustomGuideLine(HttpServletRequest request,DisasterCategory disasterCategory) {
        String username = sessionLoginService.getUserName(request);
        MemberEntity memberEntity = memberService.findMemberByUserName(username);

        return memberEntity.getCustomGuideLineEntityList().stream()
                .filter(x -> x.getDisaster().equals(disasterCategory))
                .findFirst()
                .map(GuideLineMapper::toCustomGuideLineResponse)
                .orElseThrow(() -> new DisasterCategoryNotFoundException("해당 재난에 대한 커스텀글을 생성 안했습니다"));

    }

    @Override
    @Transactional
    public void updateCustomGuideLine(HttpServletRequest request, CustomGuideLineDto customGuideLineDto) {
        String username = sessionLoginService.getUserName(request);
        MemberEntity memberEntity = memberService.findMemberByUserName(username);

        CustomGuideLineEntity customGuideLineEntity = memberEntity.getCustomGuideLineEntityList().stream()
                .filter(x -> x.getDisaster().equals(customGuideLineDto.getDisaster()))
                .findFirst()
                .orElseThrow(() -> new DisasterCategoryNotFoundException("수정하려는 재난에 대한 커스텀글을 찾지 못했습니다"));

        // 필요한 정보 업데이트
        customGuideLineEntity.updateCustomGuide(customGuideLineDto.getTitle(), customGuideLineDto.getContent());

        // 변경사항 저장
        customGuideLineRepository.save(customGuideLineEntity);
    }


}
