package com.BEACON.beacon.guideline.service;

import com.BEACON.beacon.global.error.exception.DisasterCategoryNotFoundException;
import com.BEACON.beacon.guideline.dao.BasicGuideLineRepository;
import com.BEACON.beacon.guideline.domain.BasicGuideLineEntity;
import com.BEACON.beacon.guideline.response.BasicGuideLineResponse;
import com.BEACON.beacon.scraping.domain.DisasterCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuideLineServiceImpl implements GuideLineService{
    private final BasicGuideLineRepository basicGuideLineRepository;
    @Override
    @Transactional(readOnly = true)
    public BasicGuideLineResponse getBasicGuideLine(DisasterCategory disasterCategory) {
        BasicGuideLineEntity basicGuideLineEntity = basicGuideLineRepository.findOptionalByDisaster(disasterCategory).orElseThrow(DisasterCategoryNotFoundException::new);

        return BasicGuideLineResponse.builder()
                .title(basicGuideLineEntity.getTitle())
                .content(basicGuideLineEntity.getContent())
                .build();
    }
}
