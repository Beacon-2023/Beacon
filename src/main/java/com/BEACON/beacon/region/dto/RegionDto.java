package com.BEACON.beacon.region.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class RegionDto {

    private String code;

    private String sido;

    private String sigungu;

    private String dong;

    public RegionDto(String code, String sido, String sigungu, String dong) {
        this.code = code;
        this.sido = sido;
        this.sigungu = sigungu;
        this.dong = dong;
    }
}
