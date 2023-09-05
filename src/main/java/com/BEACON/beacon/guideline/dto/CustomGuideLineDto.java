package com.BEACON.beacon.guideline.dto;

import com.BEACON.beacon.member.domain.MemberEntity;
import com.BEACON.beacon.scraping.domain.DisasterCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "회원이 작성한 커스텀 가이드라인 전달하기 위한 객체")
public class CustomGuideLineDto {


    @NotEmpty
    @Schema(description = "커스텀 가이드라인의 제목", example = "제목 예시")
    private String title;

    @NotEmpty
    @Schema(description = "커스텀 가이드라인의 내용", example = "내용 예시")
    private String content;

    @NotNull
    @Schema(description = "재난 유형", example = "CIVIL_DEFENCE/WILDFIRE/TYPHOON/FLOOD/EARTHQUAKE/ETC")
    private DisasterCategory disaster;

    public CustomGuideLineDto(){
    }

    @Builder
    public CustomGuideLineDto(String title, String content, DisasterCategory disaster) {
        this.title = title;
        this.content = content;
        this.disaster = disaster;
    }
}
