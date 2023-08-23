package com.BEACON.beacon.location.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;
@Getter
public class ApiResponse {
    @SerializedName("meta")
    private Metadata meta;

    @SerializedName("documents")
    private List<Document> documents;

    @Override
    public String toString() {
        return "ApiResponse{" +
                "meta=" + meta +
                ", documents=" + documents +
                '}';
    }
}
