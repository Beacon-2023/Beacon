package com.BEACON.beacon.scraping.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.BEACON.beacon.scraping.domain.DisasterCategory;
import com.BEACON.beacon.scraping.dto.DisasterAlertDto;
import com.BEACON.beacon.scraping.repository.ScrapingRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("스크래핑 서비스 테스트")
public class ScrapingServiceTest {

    @Mock
    private ScrapingRepository repository;

    @InjectMocks
    private ScrapingService scrapeService;

    private DisasterAlertDto dto;

    @BeforeEach
    public void setUp() {
        dto = new DisasterAlertDto(123L, DisasterCategory.EARTHQUAKE, "2022-01-01",
                "Seoul", "Disaster alert contents");
    }

    @Test
    @DisplayName("중복된 알림의 경우, DB에 저장되지 않는다")
    public void testSaveDisasterInfo_DuplicateNotification_NotSavedToDB() {
        when(repository.existsById(dto.getId())).thenReturn(true);

        // Prepare the object
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MD101_SN", dto.getId());
        jsonObject.put("DSSTR_SE_NM", dto.getDisasterCategory().toString());
        jsonObject.put("CREAT_DT", dto.getCreatedAt());
        jsonObject.put("RCV_AREA_NM", dto.getReceivedAreaName());
        jsonObject.put("MSG_CN", dto.getContent());

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);

        scrapeService.saveDisasterInfo(jsonArray);
        verify(repository, never()).save(any());
    }

}