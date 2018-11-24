package com.caramelheaven.lennach.models.model.board;

import android.os.Parcel;
import android.os.Parcelable;

import com.caramelheaven.lennach.models.model.common.DataImage;

import java.util.Objects;

public class Usenet implements Parcelable {
    //info
    private Integer filesCount;
    private Integer postsCount;
    private String threadNum;

    DataImage image;

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
                ", image=" + image +
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
        return Objects.equals(threadNum, usenet.threadNum) &&
                Objects.equals(date, usenet.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(threadNum, date);
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

    public DataImage getImage() {
        return image;
    }

    public void setImage(DataImage image) {
        this.image = image;
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
        dest.writeParcelable(this.image, flags);
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
        this.image = in.readParcelable(DataImage.class.getClassLoader());
        this.comment = in.readString();
        this.date = in.readString();
        this.name = in.readString();
        this.num = in.readString();
    }

    public static final Creator<Usenet> CREATOR = new Creator<Usenet>() {
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