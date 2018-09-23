package com.caramelheaven.lennach.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CaptchaTypeResponse {
    @SerializedName("expires")
    @Expose
    private Integer expires;
    @SerializedName("id")
    @Expose
    private String id;
}
