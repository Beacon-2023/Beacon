package com.BEACON.beacon.global.converter;

import com.BEACON.beacon.fcm.service.FcmTokenService;
import com.BEACON.beacon.scraping.domain.DisasterCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

public class StringToDisasterCategoryEnumConverter implements Converter<String,DisasterCategory> {
    private final Logger logger = LoggerFactory.getLogger(FcmTokenService.class);
    @Override
    public DisasterCategory convert(String source) {
        try {
            return DisasterCategory.valueOf(source.toUpperCase());
        }catch (IllegalArgumentException e){
            logger.info("StringToDisasterCategory: 존재하지않는 재난코드를 클라이언트에서 요청했습니다");
            return null;
        }
    }
}
