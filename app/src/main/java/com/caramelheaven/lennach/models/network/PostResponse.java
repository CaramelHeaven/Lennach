package com.caramelheaven.lennach.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostResponse {
    @SerializedName("banned")
    @Expose
    private Integer bannned;
    @SerializedName("closed")
    @Expose
    private Integer closed;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("endless")
    @Expose
    private Integer endless;
    @SerializedName("files")
    @Expose
    private List<FileResponse> files;
    @SerializedName("files_count")
    @Expose
    private Integer filesCount;
    @SerializedName("lasthit")
    @Expose
    private Integer lasthit;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("num")
    @Expose
    private String num;
    @SerializedName("op")
    @Expose
    private Integer op;
    @SerializedName("parent")
    @Expose
    private String parent;
    @SerializedName("posts_count")
    @Expose
    private String postsCount;
    @SerializedName("sticky")
    @Expose
    private Integer sticky;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("trip")
    @Expose
    private String trip;

    public Integer getBannned() {
        return bannned;
    }

    public Integer getClosed() {
        return closed;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public Integer getEndless() {
        return endless;
    }

    public List<FileResponse> getFiles() {
        return files;
    }

    public Integer getFilesCount() {
        return filesCount;
    }

    public Integer getLasthit() {
        return lasthit;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }

    public Integer getOp() {
        return op;
    }

    public String getParent() {
        return parent;
    }

    public String getPostsCount() {
        return postsCount;
    }

    public Integer getSticky() {
        return sticky;
    }

    public String getSubject() {
        return subject;
    }

    public String getTags() {
        return tags;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public String getTrip() {
        return trip;
    }
}
