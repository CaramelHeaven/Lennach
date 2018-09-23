package com.caramelheaven.lennach.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FileResponse {
    @SerializedName("displayname")
    @Expose
    private String displayname;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("duration_secs")
    @Expose
    private String durationSecs;
    @SerializedName("fullname")
    @Expose
    private String fullName;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("md5")
    @Expose
    private String md5;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nsfw")
    @Expose
    private String nsfw;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("size")
    @Expose
    private int size;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("tn_height")
    @Expose
    private String tnHeight;
    @SerializedName("tn_width")
    @Expose
    private String tnWidth;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("width")
    @Expose
    private int width;

    public String getDisplayname() {
        return displayname;
    }

    public String getDuration() {
        return duration;
    }

    public String getDurationSecs() {
        return durationSecs;
    }

    public String getFullName() {
        return fullName;
    }

    public int getHeight() {
        return height;
    }

    public String getMd5() {
        return md5;
    }

    public String getName() {
        return name;
    }

    public String getNsfw() {
        return nsfw;
    }

    public String getPath() {
        return path;
    }

    public int getSize() {
        return size;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTnHeight() {
        return tnHeight;
    }

    public String getTnWidth() {
        return tnWidth;
    }

    public String getType() {
        return type;
    }

    public int getWidth() {
        return width;
    }
}
