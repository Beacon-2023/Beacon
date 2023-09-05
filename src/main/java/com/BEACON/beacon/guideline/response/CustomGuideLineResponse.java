package com.BEACON.beacon.guideline.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "커스텀 가이드라인 응답 객체")
public class CustomGuideLineResponse {
    @Schema(description = "커스텀 가이드라인의 제목", example = "제목 예시")
    private String title;
    @Schema(description = "커스텀 가이드라인의 내용", example = "내용 예시")
    private String content;
    @Schema(description = "재난 유형", example = "CIVIL_DEFENCE/WILDFIRE/TYPHOON/FLOOD/EARTHQUAKE/ETC")
    private String disaster;

    public CustomGuideLineResponse(){
    }
    @Builder
    public CustomGuideLineResponse(String title, String content,String disaster) {
        this.title = title;
        this.content = content;
        this.disaster = disaster;
    }
}
