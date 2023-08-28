package com.BEACON.beacon.location.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;
@Getter
public class KakaoApiResponse {
    @SerializedName("meta")
    private KakaoMetaData meta;

    @SerializedName("documents")
    private List<KakaoDocument> documents;

    @Override
    public String toString() {
        return "ApiResponse{" +
                "meta=" + meta +
                ", documents=" + documents +
                '}';
    }
}
