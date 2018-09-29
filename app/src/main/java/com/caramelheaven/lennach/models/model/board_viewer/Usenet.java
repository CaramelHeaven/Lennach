package com.caramelheaven.lennach.models.model.board_viewer;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Usenet implements Parcelable {
    //info
    private Integer filesCount;
    private Integer postsCount;
    private String threadNum;

    //image
    private String displayNameImage;
    private String nameImage;
    private Integer sizeImage;
    private String thumbnail;
    private Integer widthImage;
    private Integer heightImage;

    //post
    private String comment;
    private String date;
    private String name;
    private String num;

    @Override
    public String toString() {
        return "Usenet{" +
                "filesCount=" + filesCount +
                ", postsCount=" + postsCount +
                ", threadNum='" + threadNum + '\'' +
                ", displayNameImage='" + displayNameImage + '\'' +
                ", nameImage='" + nameImage + '\'' +
                ", sizeImage=" + sizeImage +
                ", thumbnail='" + thumbnail + '\'' +
                ", widthImage=" + widthImage +
                ", heightImage=" + heightImage +
                ", comment='" + comment + '\'' +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", num='" + num + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usenet usenet = (Usenet) o;
        return Objects.equals(threadNum, usenet.threadNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(threadNum);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setFilesCount(Integer filesCount) {
        this.filesCount = filesCount;
    }

    public Integer getFilesCount() {
        return filesCount;
    }

    public Integer getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(Integer postsCount) {
        this.postsCount = postsCount;
    }

    public String getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(String threadNum) {
        this.threadNum = threadNum;
    }

    public String getDisplayNameImage() {
        return displayNameImage;
    }

    public void setDisplayNameImage(String displayNameImage) {
        this.displayNameImage = displayNameImage;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public Integer getSizeImage() {
        return sizeImage;
    }

    public void setSizeImage(Integer sizeImage) {
        this.sizeImage = sizeImage;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getWidthImage() {
        return widthImage;
    }

    public void setWidthImage(Integer widthImage) {
        this.widthImage = widthImage;
    }

    public Integer getHeightImage() {
        return heightImage;
    }

    public void setHeightImage(Integer heightImage) {
        this.heightImage = heightImage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.filesCount);
        dest.writeValue(this.postsCount);
        dest.writeString(this.threadNum);
        dest.writeString(this.displayNameImage);
        dest.writeString(this.nameImage);
        dest.writeValue(this.sizeImage);
        dest.writeString(this.thumbnail);
        dest.writeValue(this.widthImage);
        dest.writeValue(this.heightImage);
        dest.writeString(this.comment);
        dest.writeString(this.date);
        dest.writeString(this.name);
        dest.writeString(this.num);
    }

    public Usenet() {
    }

    protected Usenet(Parcel in) {
        this.filesCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.postsCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.threadNum = in.readString();
        this.displayNameImage = in.readString();
        this.nameImage = in.readString();
        this.sizeImage = (Integer) in.readValue(Integer.class.getClassLoader());
        this.thumbnail = in.readString();
        this.widthImage = (Integer) in.readValue(Integer.class.getClassLoader());
        this.heightImage = (Integer) in.readValue(Integer.class.getClassLoader());
        this.comment = in.readString();
        this.date = in.readString();
        this.name = in.readString();
        this.num = in.readString();
    }

    public static final Parcelable.Creator<Usenet> CREATOR = new Parcelable.Creator<Usenet>() {
        @Override
        public Usenet createFromParcel(Parcel source) {
            return new Usenet(source);
        }

        @Override
        public Usenet[] newArray(int size) {
            return new Usenet[size];
        }
    };
}
