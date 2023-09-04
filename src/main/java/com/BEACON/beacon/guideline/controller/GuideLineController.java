package com.BEACON.beacon.guideline.controller;

import com.BEACON.beacon.global.annotation.LoginRequired;
import com.BEACON.beacon.guideline.dto.CustomGuideLineDto;
import com.BEACON.beacon.guideline.response.BasicGuideLineResponse;
import com.BEACON.beacon.guideline.response.CustomGuideLineResponse;
import com.BEACON.beacon.guideline.service.GuideLineService;
import com.BEACON.beacon.scraping.domain.DisasterCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.BEACON.beacon.global.HttpStatusResponse.RESPONSE_CREATED;
import static com.BEACON.beacon.global.HttpStatusResponse.RESPONSE_OK;

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


    /**
     * 커스텀가이드라인을 등록합니다
     * 201: 등록 성공
     * @param customGuideLineDto (회원아이디,제목,내용,재난유형)
     * @return
     */
    @Operation(summary = "커스텀 가이드라인 등록", description = "커스텀 가이드라인 정보를 보내면 해당 정보로 커스텀가이드라인을 생성합니다")
    @ApiResponse(responseCode = "201", description = "커스텀가이드라인 생성 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청, 가이드라인 정보를 확인하세요")
    @LoginRequired
    @PostMapping("/guidelines/customs")
    public ResponseEntity<HttpStatus> registerCustomGuideLine(@RequestBody @Valid CustomGuideLineDto customGuideLineDto
        ,HttpServletRequest request
    ){
        guideLineService.registerCustomGuideLine(customGuideLineDto,request);

        return RESPONSE_CREATED;
    }

    /**
     * 회원이 등록한 모든 커스텀 가이드라인을 불러옵니다
     * @param
     * @return
     */
    @Operation(summary = "모든 커스텀 가이드라인 불러오기", description = "회원이 등록한 모든 커스텀 가이드라인을 응답받습니다")
    @ApiResponse(responseCode = "200", description = "커스텀가이드라인 불러오기 성공")
    @LoginRequired
    @GetMapping("/guidelines/customs")
    public List<CustomGuideLineResponse> getAllCustomGuideLines(HttpServletRequest request){
       return guideLineService.getAllCustomGuideLine(request);
    }

    /**
     * 회원이 요청한 재난의 커스텀 가이드라인을 불러옵니다
     * @param disasterCategory
     * @param request
     * @return
     */
    @Operation(summary = "커스텀 가이드라인 불러오기", description = "재난명을 보내면 해당 재난명에 해당하는 커스텀가이드라인을 응답받습니다")
    @ApiResponse(responseCode = "200", description = "커스텀가이드라인 불러오기 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청, 재난명을 확인하세요")
    @LoginRequired
    @GetMapping("/guidelines/customs/{disaster}")
    public ResponseEntity<CustomGuideLineResponse> getCustomGuideLine(
            @Parameter(description="CIVIL_DEFENCE,WILDFIRE,TYPHOON,FLOOD,EARTHQUAKE,ETC 의 값만 가능합니다")
            @PathVariable("disaster")DisasterCategory disasterCategory,
            HttpServletRequest request
    ){
        if(disasterCategory==null){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(guideLineService.getCustomGuideLine(request,disasterCategory),HttpStatus.OK);
    }


    @Operation(summary = "커스텀 가이드라인 수정", description = "회원이 만든 재난명과 해당 재난에 대한 커스텀 가이드라인 정보를 보내면 해당 재난명에 해당하는 커스텀가이드라인을 수정합니다")
    @ApiResponse(responseCode = "200", description = "커스텀가이드라인 수정 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청, 재난명 및 가이드라인 정보를 확인하세요")
    @LoginRequired
    @PutMapping("/guidelines/customs")
    public ResponseEntity<HttpStatus> updateCustomGuideLine(
            @Parameter(description="수정할 커스텀 가이드라인 정보")
            @RequestBody @Valid CustomGuideLineDto customGuideLineDto
            ,HttpServletRequest request
    ){
        guideLineService.updateCustomGuideLine(request,customGuideLineDto);

        return RESPONSE_OK;
    }

}
