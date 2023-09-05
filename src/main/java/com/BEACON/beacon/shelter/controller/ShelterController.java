package com.BEACON.beacon.shelter.controller;

import com.BEACON.beacon.shelter.dto.ShelterRequestDto;
import com.BEACON.beacon.shelter.dto.ShelterResponseDto;
import com.BEACON.beacon.shelter.mapper.ShelterMapper;
import com.BEACON.beacon.shelter.service.ShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "대피소", description = "대피소 관련 api입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shelters")
public class ShelterController {

    private final ShelterService shelterService;

    @Operation(summary = "대피소 검색", description = "사용자로부터 가장 가까운 대피소를 count개만큼 반환합니다.")
    @ApiResponse(responseCode = "200", description = "대피소 정상 반환")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    @Parameter(name = "x", description = "반환할 대피소 개수")
    @Parameter(name = "y", description = "반환할 대피소 개수")
    @Parameter(name = "count", description = "반환할 대피소 개수")
    @Parameter(name = "shelterCategory", description = "요청할 대피소 종류. Schema 참조")
    @PostMapping
    public ResponseEntity<List<ShelterResponseDto>> findShelters(@Valid @RequestBody ShelterRequestDto dto) {
        List<ShelterResponseDto> list = shelterService.findNearestShelters(dto.getX(), dto.getY(),
                        dto.getShelterCategory(), dto.getCount())
                .stream()
                .map(ShelterMapper::toResponseDto)
                .toList();

        return ResponseEntity.ok().body(list);
    }

}
