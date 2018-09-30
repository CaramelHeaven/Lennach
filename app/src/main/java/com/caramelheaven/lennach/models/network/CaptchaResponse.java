package com.caramelheaven.lennach.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CaptchaResponse {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("type")
    @Expose
    private String type;

    public String getId() {
        return id;
    }

    public Integer getResult() {
        return result;
    }

    public String getType() {
        return type;
    }
}
