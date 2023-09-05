package com.BEACON.beacon.region.service;

import com.BEACON.beacon.region.domain.RegionAlert;
import com.BEACON.beacon.region.dto.RegionAlertDto;
import com.BEACON.beacon.region.mapper.RegionMapper;
import com.BEACON.beacon.region.exception.NoSuchRegionAlertException;
import com.BEACON.beacon.region.repository.RegionAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionAlertService {

    private final RegionAlertRepository repository;

    @Transactional
    public Long saveRegionAlert(RegionAlertDto dto) {
        RegionAlert entity = RegionMapper.toEntity(dto);
        RegionAlert savedRegionAlert = repository.save(entity);

        return savedRegionAlert.getId();
    }

    public RegionAlertDto findRegionAlertById(Long id) {
        RegionAlert regionAlert = repository.findRegionAlertById(id).orElseThrow(
                () -> new NoSuchRegionAlertException("해당하는 지역 재난알람이 존재하지 않습니다.")
        );

        return RegionMapper.toDto(regionAlert);
    }

}
