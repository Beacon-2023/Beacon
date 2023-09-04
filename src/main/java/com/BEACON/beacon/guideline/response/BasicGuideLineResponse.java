package com.BEACON.beacon.guideline.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "커스텀 가이드라인 응답 객체")
public class BasicGuideLineResponse {
    private String title;
    private String content;

    @Builder
    public BasicGuideLineResponse(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
