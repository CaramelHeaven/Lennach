package com.caramelheaven.lennach.models.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.caramelheaven.lennach.models.model.common.DataImage;

import java.util.Objects;

/**
 * Created by CaramelHeaven on 16:21, 03/02/2019.
 */
public class Usenet implements Parcelable {
    //info
    private Integer filesCount;
    private Integer postsCount;
    private String threadNum;

    private DataImage image;

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
        return Objects.equals(filesCount, usenet.filesCount) &&
                Objects.equals(postsCount, usenet.postsCount) &&
                Objects.equals(threadNum, usenet.threadNum) &&
                Objects.equals(image, usenet.image) &&
                Objects.equals(comment, usenet.comment) &&
                Objects.equals(date, usenet.date) &&
                Objects.equals(name, usenet.name) &&
                Objects.equals(num, usenet.num);
    }

    @Override
    public int hashCode() {

        return Objects.hash(filesCount, postsCount, threadNum, image, comment, date, name, num);
    }

    public Integer getFilesCount() {
        return filesCount;
    }

    public void setFilesCount(Integer filesCount) {
        this.filesCount = filesCount;
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
