package com.caramelheaven.lennach.models.model.board_viewer;

import java.util.Objects;

public class Usenet {
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
}
