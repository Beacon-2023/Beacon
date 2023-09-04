package com.BEACON.beacon.guideline.service;

import com.BEACON.beacon.guideline.dto.CustomGuideLineDto;
import com.BEACON.beacon.guideline.response.BasicGuideLineResponse;
import com.BEACON.beacon.guideline.response.CustomGuideLineResponse;
import com.BEACON.beacon.scraping.domain.DisasterCategory;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface GuideLineService {
    public BasicGuideLineResponse getBasicGuideLine(DisasterCategory disasterCategory);

    public void registerCustomGuideLine(CustomGuideLineDto customGuideLineDto, HttpServletRequest request);

    public List<CustomGuideLineResponse> getAllCustomGuideLine(HttpServletRequest request);

    public CustomGuideLineResponse getCustomGuideLine(HttpServletRequest request,DisasterCategory disasterCategory);

    public void updateCustomGuideLine(HttpServletRequest request,CustomGuideLineDto customGuideLineDto);
}
