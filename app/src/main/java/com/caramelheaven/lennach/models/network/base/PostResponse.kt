package com.caramelheaven.lennach.models.network.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by CaramelHeaven on 16:17, 03/02/2019.
 */
class PostResponse {
    @SerializedName("banned")
    @Expose
    val bannned: Int? = null
    @SerializedName("closed")
    @Expose
    val closed: Int? = null
    @SerializedName("comment")
    @Expose
    val comment: String? = null
    @SerializedName("date")
    @Expose
    val date: String? = null
    @SerializedName("email")
    @Expose
    val email: String? = null
    @SerializedName("endless")
    @Expose
    val endless: Int? = null
    @SerializedName("files")
    @Expose
    val files: List<FileResponse>? = null
    @SerializedName("files_count")
    @Expose
    val filesCount: Int? = null
    @SerializedName("lasthit")
    @Expose
    val lasthit: Int? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
    @SerializedName("num")
    @Expose
    val num: String? = null
    @SerializedName("op")
    @Expose
    val op: Int? = null
    @SerializedName("parent")
    @Expose
    val parent: String? = null
    @SerializedName("posts_count")
    @Expose
    val postsCount: String? = null
    @SerializedName("sticky")
    @Expose
    val sticky: Int? = null
    @SerializedName("subject")
    @Expose
    val subject: String? = null
    @SerializedName("tags")
    @Expose
    val tags: String? = null
    @SerializedName("timestamp")
    @Expose
    val timestamp: Int? = null
    @SerializedName("trip")
    @Expose
    val trip: String? = null
}
