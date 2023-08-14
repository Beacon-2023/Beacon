package com.BEACON.beacon.scraping.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.BEACON.beacon.scraping.dto.DisasterAlertDto;
import com.BEACON.beacon.scraping.exception.DuplicatedAlertException;
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
        dto = new DisasterAlertDto(123L, "DisasterName", "2022-01-01",
                "Seoul", "Disaster alert contents");
    }

    @Test
    @DisplayName("중복된 알림의 경우, ")
    public void whenSavingDuplicateDisasterAlert_thenThrowsUnsupportedOperationException() {
        when(repository.existsById(dto.getId())).thenReturn(true);

        // Prepare the object
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MD101_SN", dto.getId());
        jsonObject.put("DSSTR_SE_NM", dto.getDisasterName());
        jsonObject.put("CREAT_DT", dto.getCreatedAt());
        jsonObject.put("RCV_AREA_NM", dto.getReceivedAreaName());
        jsonObject.put("MSG_CN", dto.getContent());

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);

        // Test the saveDisasterInfo method
        assertThrows(
                DuplicatedAlertException.class,
                () -> scrapeService.saveDisasterInfo(jsonArray));
    }
}