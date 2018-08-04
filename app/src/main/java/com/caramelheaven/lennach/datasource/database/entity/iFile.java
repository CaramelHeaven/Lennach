package com.caramelheaven.lennach.datasource.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(foreignKeys = @ForeignKey(entity = iPost.class, parentColumns = "postId",
        childColumns = "idPost", onDelete = ForeignKey.CASCADE))
public class iFile {
    @NotNull
    @PrimaryKey
    private String fileId;

    private String displayName;
    private String fullName;
    private int height;
    private int width;
    private String path;
    private int size;
    private String thumbnail;

    private String idPost;

    public iFile(@NotNull String fileId, String displayName, String fullName, int height, int width, String path, int size, String thumbnail, String idPost) {
        this.fileId = fileId;
        this.displayName = displayName;
        this.fullName = fullName;
        this.height = height;
        this.width = width;
        this.path = path;
        this.size = size;
        this.thumbnail = thumbnail;
        this.idPost = idPost;
    }

    @Override
    public String toString() {
        return "iFile{" +
                "fileId='" + fileId + '\'' +
                ", displayName='" + displayName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", thumbnail='" + thumbnail + '\'' +
                ", idPost='" + idPost + '\'' +
                '}';
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    @NotNull
    public String getFileId() {
        return fileId;
    }

    public void setFileId(@NotNull String fileId) {
        this.fileId = fileId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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

    public void setSize(int size) {
        this.size = size;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
