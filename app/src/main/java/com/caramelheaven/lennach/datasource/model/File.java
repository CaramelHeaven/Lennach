package com.caramelheaven.lennach.datasource.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class File implements Parcelable {
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

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDurationSecs() {
        return durationSecs;
    }

    public void setDurationSecs(String durationSecs) {
        this.durationSecs = durationSecs;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getHeight() {
        return height;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNsfw() {
        return nsfw;
    }

    public void setNsfw(String nsfw) {
        this.nsfw = nsfw;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTnHeight() {
        return tnHeight;
    }

    public void setTnHeight(String tnHeight) {
        this.tnHeight = tnHeight;
    }

    public String getTnWidth() {
        return tnWidth;
    }

    public void setTnWidth(String tnWidth) {
        this.tnWidth = tnWidth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public File() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.displayname);
        dest.writeString(this.duration);
        dest.writeString(this.durationSecs);
        dest.writeString(this.fullName);
        dest.writeInt(this.height);
        dest.writeString(this.md5);
        dest.writeString(this.name);
        dest.writeString(this.nsfw);
        dest.writeString(this.path);
        dest.writeInt(this.size);
        dest.writeString(this.thumbnail);
        dest.writeString(this.tnHeight);
        dest.writeString(this.tnWidth);
        dest.writeString(this.type);
        dest.writeInt(this.width);
    }

    protected File(Parcel in) {
        this.displayname = in.readString();
        this.duration = in.readString();
        this.durationSecs = in.readString();
        this.fullName = in.readString();
        this.height = in.readInt();
        this.md5 = in.readString();
        this.name = in.readString();
        this.nsfw = in.readString();
        this.path = in.readString();
        this.size = in.readInt();
        this.thumbnail = in.readString();
        this.tnHeight = in.readString();
        this.tnWidth = in.readString();
        this.type = in.readString();
        this.width = in.readInt();
    }

    public static final Creator<File> CREATOR = new Creator<File>() {
        @Override
        public File createFromParcel(Parcel source) {
            return new File(source);
        }

        @Override
        public File[] newArray(int size) {
            return new File[size];
        }
    };
}
