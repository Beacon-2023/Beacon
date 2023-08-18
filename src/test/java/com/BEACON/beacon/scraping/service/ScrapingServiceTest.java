package com.BEACON.beacon.scraping.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.BEACON.beacon.region.service.RegionAlertService;
import com.BEACON.beacon.region.service.RegionService;
import com.BEACON.beacon.scraping.domain.DisasterCategory;
import com.BEACON.beacon.scraping.dto.DisasterAlertDto;
import com.BEACON.beacon.scraping.repository.ScrapingRepository;
import java.io.IOException;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("스크래핑 서비스 테스트")
public class ScrapingServiceTest {

    ScrapingService scrapingService;
    ScrapingRepository scrapingRepository;

    @BeforeEach
    void setup() {
        scrapingRepository = Mockito.mock(ScrapingRepository.class);
        scrapingService = new ScrapingService(null, null, scrapingRepository);
    }

    @Test
    void Given_DisasterAlertDto_When_isUniqueAlertCalled_Then_CheckIfItInteractsWithRepo() {
        // Arrange
        DisasterAlertDto disasterAlertDto = new DisasterAlertDto(1L, null, null, null, null);
        when(scrapingRepository.existsById(anyLong())).thenReturn(false);

        // Act
        boolean isUnique = scrapingService.isUniqueAlert(disasterAlertDto);

        // Assert
        assertTrue(isUnique);
        verify(scrapingRepository, times(1)).existsById(disasterAlertDto.getId());
    }

}