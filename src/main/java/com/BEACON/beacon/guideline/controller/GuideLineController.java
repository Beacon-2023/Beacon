package com.BEACON.beacon.guideline.controller;

import com.BEACON.beacon.guideline.response.BasicGuideLineResponse;
import com.BEACON.beacon.guideline.service.GuideLineService;
import com.BEACON.beacon.scraping.domain.DisasterCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Guideline")
public class GuideLineController {

    private final GuideLineService guideLineService;

    /**
     * 클라이언트에서 disasterCategory를 전달하면 해당 카테고리에 해당하는 기본가이드라인을 제공합니다
     * 200: OK
     * 400: 비정상적인 경로
     * @param disasterCategory
     * @return
     */
    @Operation(summary = "기본 가이드라인 불러오기", description = "재난명을 보내면 해당 재난명에 해당하는 기본가이드라인을 응답받습니다")
    @ApiResponse(responseCode = "200", description = "기본가이드라인 불러오기 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청, 재난명을 확인하세요")
    @GetMapping("/guidelines/{disaster}")
    public ResponseEntity<BasicGuideLineResponse> getBasicGuideLine(
            @Parameter(description="CIVIL_DEFENCE,WILDFIRE,TYPHOON,FLOOD,EARTHQUAKE,ETC 의 값만 가능합니다")
            @PathVariable("disaster")DisasterCategory disasterCategory
    ){
        if(disasterCategory==null){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

        BasicGuideLineResponse basicGuideLine = guideLineService.getBasicGuideLine(disasterCategory);

        return new ResponseEntity<>(basicGuideLine,HttpStatus.OK);
    }

}
