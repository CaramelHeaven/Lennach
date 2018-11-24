package com.caramelheaven.lennach.models.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FileResponse {
    @SerializedName("displayname")
    @Expose
    val displayname: String? = null
    @SerializedName("duration")
    @Expose
    val duration: String? = null
    @SerializedName("duration_secs")
    @Expose
    val durationSecs: String? = null
    @SerializedName("fullname")
    @Expose
    val fullName: String? = null
    @SerializedName("height")
    @Expose
    val height: Int = 0
    @SerializedName("md5")
    @Expose
    val md5: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
    @SerializedName("nsfw")
    @Expose
    val nsfw: String? = null
    @SerializedName("path")
    @Expose
    val path: String? = null
    @SerializedName("size")
    @Expose
    val size: Int = 0
    @SerializedName("thumbnail")
    @Expose
    val thumbnail: String? = null
    @SerializedName("tn_height")
    @Expose
    val tnHeight: String? = null
    @SerializedName("tn_width")
    @Expose
    val tnWidth: String? = null
    @SerializedName("type")
    @Expose
    val type: String? = null
    @SerializedName("width")
    @Expose
    val width: Int = 0
}
