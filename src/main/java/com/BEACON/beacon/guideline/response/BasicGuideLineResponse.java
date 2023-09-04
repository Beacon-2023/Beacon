package com.BEACON.beacon.guideline.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasicGuideLineResponse {
    private String title;
    private String content;

    @Builder
    public BasicGuideLineResponse(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
