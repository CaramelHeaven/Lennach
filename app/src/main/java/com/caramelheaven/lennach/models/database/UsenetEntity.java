package com.caramelheaven.lennach.models.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "UsenetTable")
public class UsenetEntity {
    @PrimaryKey
    private int usenetId;

    //info
    @ColumnInfo(name = "files_count")
    private Integer filesCount;
    @ColumnInfo(name = "posts_count")
    private Integer postsCount;
    @ColumnInfo(name = "thread_num")
    private String threadNum;

    //image
    @ColumnInfo(name = "display_name_image")
    private String displayNameImage;
    @ColumnInfo(name = "name_image")
    private String nameImage;
    @ColumnInfo(name = "size_image")
    private Integer sizeImage;
    @ColumnInfo(name = "thumbnail")
    private String thumbnail;
    @ColumnInfo(name = "width_image")
    private Integer widthImage;
    @ColumnInfo(name = "height_image")
    private Integer heightImage;

    //post
    @ColumnInfo(name = "comment")
    private String comment;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "num")
    private String num;

    public UsenetEntity() {

    }

    public int getUsenetId() {
        return usenetId;
    }

    public void setUsenetId(int usenetId) {
        this.usenetId = usenetId;
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
}
