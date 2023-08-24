package com.BEACON.beacon.shelter.service;

import com.BEACON.beacon.shelter.domain.Shelter;
import com.BEACON.beacon.shelter.domain.ShelterCategory;
import com.BEACON.beacon.shelter.dto.ShelterDto;
import com.BEACON.beacon.shelter.exception.NoSuchShelterException;
import com.BEACON.beacon.shelter.mapper.ShelterMapper;
import com.BEACON.beacon.shelter.repository.ShelterRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShelterService {

    private final ShelterRepository repository;

    public ShelterDto findShelterById(Long id) {
        Shelter shelter = repository.findShelterById(id).orElseThrow(
                () -> new NoSuchShelterException("해당하는 대피장소가 존재하지 않습니다.")
        );

        return ShelterMapper.toDto(shelter);
    }

    public List<ShelterDto> findNearestShelters(Double x, Double y, ShelterCategory category,
            Integer count) {
        return repository.findNearestShelters(x, y, category, count).stream()
                .map(ShelterMapper::toDto)
                .collect(Collectors.toList());
    }
}
