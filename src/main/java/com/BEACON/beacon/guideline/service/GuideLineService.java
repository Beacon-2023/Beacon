package com.BEACON.beacon.guideline.service;

import com.BEACON.beacon.guideline.response.BasicGuideLineResponse;
import com.BEACON.beacon.scraping.domain.DisasterCategory;

public interface GuideLineService {
    public BasicGuideLineResponse getBasicGuideLine(DisasterCategory disasterCategory);
}
