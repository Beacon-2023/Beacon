package com.BEACON.beacon.scraping.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.BEACON.beacon.scraping.domain.DisasterAlert;
import com.BEACON.beacon.scraping.repository.ScrapingRepository;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ScrapingServiceTest {

    @Autowired
    private ScrapingRepository repository;

    @Autowired
    private ScrapingService service;

    @Test
    @DisplayName("Test scraping disaster info functionality")
    void testScrapingDisasterInfo() throws Exception {
        // given
        Map<String, String> cookies = new HashMap<>();
        Connection.Response mockResponse = mock(Connection.Response.class);

        JSONObject mockJson = new JSONObject();
        JSONArray mockArray = new JSONArray();
        JSONObject mockObject = new JSONObject();
        mockObject.put("MD101_SN", 123456);
        mockObject.put("DSSTR_SE_NM", "Test Disaster");
        mockObject.put("CREAT_DT", "2023-08-11");
        mockObject.put("RCV_AREA_NM", "Test Area");
        mockObject.put("MSG_CN", "Test content");
        mockArray.put(mockObject);
        mockJson.put("disasterSmsList", mockArray);

        when(mockResponse.body()).thenReturn(mockJson.toString());
        when(repository.save(any(DisasterAlert.class))).thenReturn(null);

        // when
        service.scrapingDisasterInfo();

        // then
        verify(repository, times(1)).save(any(DisasterAlert.class));
    }
}