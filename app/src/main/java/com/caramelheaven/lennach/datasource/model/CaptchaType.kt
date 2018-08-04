package com.caramelheaven.lennach.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CaptchaType {
    @SerializedName("expires")
    @Expose
    var expires: Int? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
}