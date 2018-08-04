package com.caramelheaven.lennach.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Captcha {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("result")
    @Expose
    var result: Int? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
}
