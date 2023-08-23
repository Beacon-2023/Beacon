package com.BEACON.beacon.location.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Metadata {
    @SerializedName("total_count")
    private int totalCount;

    @Override
    public String toString() {
        return "Metadata{" +
                "totalCount=" + totalCount +
                '}';
    }
}
