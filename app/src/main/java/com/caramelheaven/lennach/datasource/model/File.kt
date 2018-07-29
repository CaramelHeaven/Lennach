package com.caramelheaven.lennach.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by CaramelHeaven on 27.07.2018
 */
class File {
    @SerializedName("displayname")
    @Expose
    var displayname: String? = null
    @SerializedName("duration")
    @Expose
    var duration: String? = null
    @SerializedName("duration_secs")
    @Expose
    var durationSecs: Int? = null
    @SerializedName("fullname")
    @Expose
    var fullname: String? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("md5")
    @Expose
    var md5: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("nsfw")
    @Expose
    var nsfw: Int? = null
    @SerializedName("path")
    @Expose
    var path: String? = null
    @SerializedName("size")
    @Expose
    var size: Int? = null
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null
    @SerializedName("tn_height")
    @Expose
    var tnHeight: Int? = null
    @SerializedName("tn_width")
    @Expose
    var tnWidth: Int? = null
    @SerializedName("type")
    @Expose
    var type: Int? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null
}
