package com.BEACON.beacon.region.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.BEACON.beacon.region.domain.Region;
import com.BEACON.beacon.region.dto.RegionDto;
import com.BEACON.beacon.region.exception.NoSuchRegionException;
import com.BEACON.beacon.region.repository.RegionRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegionServiceTest {

    @InjectMocks
    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @Test
    @DisplayName("DB에 존재하는 지역 코드일 경우 RegionDto 반환")
    void findRegionByCode() {
        // given
        String testCode = "1234";
        Region expectedRegion = new Region();
        when(regionRepository.findRegionByCode(testCode)).thenReturn(Optional.of(expectedRegion));

        // when
        RegionDto result = regionService.findRegionByCode(testCode);

        // then
        verify(regionRepository, times(1)).findRegionByCode(testCode);
        assertNotNull(result);
    }

    @Test
    @DisplayName("DB에 존재하지 않는 지역 코드일 경우 NoSuchRegionException 반환")
    void findRegionByCode_notExistRegion() {
        // given
        String testCode = "1234";
        when(regionRepository.findRegionByCode(testCode)).thenReturn(Optional.empty());

        // when, then
        Exception exception = assertThrows(NoSuchRegionException.class,
                () -> regionService.findRegionByCode(testCode));
        assertTrue(exception.getMessage().contains("코드에 맞는 지역이 존재하지 않습니다."));
    }

}