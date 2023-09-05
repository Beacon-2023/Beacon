package com.BEACON.beacon.region.service;

import com.BEACON.beacon.region.domain.Region;
import com.BEACON.beacon.region.dto.RegionDto;
import com.BEACON.beacon.region.mapper.RegionMapper;
import com.BEACON.beacon.region.exception.NoSuchRegionException;
import com.BEACON.beacon.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionService {

    private final RegionRepository repository;

    public RegionDto findRegionByCode(Integer code) {
        Region region = repository.findRegionByCode(code).orElseThrow(
                () -> new NoSuchRegionException("코드에 맞는 지역이 존재하지 않습니다.")
        );
        return RegionMapper.toDto(region);
    }

}
