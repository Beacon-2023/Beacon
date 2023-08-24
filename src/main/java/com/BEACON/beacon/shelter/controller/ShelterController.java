package com.BEACON.beacon.shelter.controller;

import com.BEACON.beacon.shelter.dto.ShelterRequestDto;
import com.BEACON.beacon.shelter.dto.ShelterResponseDto;
import com.BEACON.beacon.shelter.mapper.ShelterMapper;
import com.BEACON.beacon.shelter.service.ShelterService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shelters")
public class ShelterController {

    private final ShelterService shelterService;

    @GetMapping
    public ResponseEntity<List<ShelterResponseDto>> findShelters(
            @RequestBody @Valid ShelterRequestDto dto) {
        List<ShelterResponseDto> list = shelterService.findNearestShelters(dto.getX(), dto.getY(),
                        dto.getShelterCategory(), dto.getCount())
                .stream()
                .map(ShelterMapper::toResponseDto)
                .toList();

        return ResponseEntity.ok().body(list);
    }

}
