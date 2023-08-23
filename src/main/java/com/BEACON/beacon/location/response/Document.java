package com.BEACON.beacon.location.response;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Document {
    @SerializedName("region_type")
    private String regionType;

    @SerializedName("address_name")
    private String addressName;

    @SerializedName("region_1depth_name")
    private String region1depthName;

    @SerializedName("region_2depth_name")
    private String region2depthName;

    @SerializedName("region_3depth_name")
    private String region3depthName;

    @SerializedName("region_4depth_name")
    private String region4depthName;

    @SerializedName("code")
    private String code;


    @Override
    public String toString() {
        return "Document{" +
                "regionType='" + regionType + '\'' +
                ", addressName='" + addressName + '\'' +
                ", region1depthName='" + region1depthName + '\'' +
                ", region2depthName='" + region2depthName + '\'' +
                ", region3depthName='" + region3depthName + '\'' +
                ", region4depthName='" + region4depthName + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
