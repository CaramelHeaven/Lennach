package com.caramelheaven.lennach.models.model.thread;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.View;

import com.caramelheaven.lennach.models.model.common.DataImage;
import com.caramelheaven.lennach.utils.OnAnswerItemClickListener;

import java.util.List;
import java.util.Objects;

public class Post implements Parcelable {
    private String comment;
    private String date;
    private String name;
    private String num;

    private Integer bannned;
    private Integer lasthit;
    private Integer op;
    private String email;
    private Integer filesCount;
    private String tags;

    //Set this string to tv viewHolder
    private SpannableString modernComment;

    //This list contains all replies num to this post
    private List<String> repliesPostList;

    /* Click listener where we install it on thread fragment.
     * */
    public OnAnswerItemClickListener onAnswerItemClickListener;

    /* Images
     * */
    private List<DataImage> files;

    @Override
    public String toString() {
        return "Post{" +
                "comment='" + comment + '\'' +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", num='" + num + '\'' +
                ", bannned=" + bannned +
                ", lasthit=" + lasthit +
                ", op=" + op +
                ", email='" + email + '\'' +
                ", filesCount=" + filesCount +
                ", tags='" + tags + '\'' +
                ", modernComment=" + modernComment +
                ", repliesPostList=" + repliesPostList +
                ", onAnswerItemClickListener=" + onAnswerItemClickListener +
                ", files=" + files +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(num, post.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }


    public void setOnAnswerItemClickListener(OnAnswerItemClickListener onAnswerItemClickListener) {
        this.onAnswerItemClickListener = onAnswerItemClickListener;
    }

    public SpannableString getModernComment() {
        return modernComment;
    }

    public void setModernComment(SpannableString modernComment) {
        this.modernComment = modernComment;
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

    public Integer getBannned() {
        return bannned;
    }

    public void setBannned(Integer bannned) {
        this.bannned = bannned;
    }

    public Integer getLasthit() {
        return lasthit;
    }

    public void setLasthit(Integer lasthit) {
        this.lasthit = lasthit;
    }

    public Integer getOp() {
        return op;
    }

    public void setOp(Integer op) {
        this.op = op;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFilesCount() {
        return filesCount;
    }

    public void setFilesCount(Integer filesCount) {
        this.filesCount = filesCount;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<DataImage> getFiles() {
        return files;
    }

    public void setFiles(List<DataImage> files) {
        this.files = files;
    }

    public List<String> getRepliesPostList() {
        return repliesPostList;
    }

    public void setRepliesPostList(List<String> repliesPostList) {
        this.repliesPostList = repliesPostList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.comment);
        dest.writeString(this.date);
        dest.writeString(this.name);
        dest.writeString(this.num);
        dest.writeValue(this.bannned);
        dest.writeValue(this.lasthit);
        dest.writeValue(this.op);
        dest.writeString(this.email);
        dest.writeValue(this.filesCount);
        dest.writeString(this.tags);
        dest.writeStringList(this.repliesPostList);
        dest.writeTypedList(this.files);
    }

    public Post() {
    }

    protected Post(Parcel in) {
        this.comment = in.readString();
        this.date = in.readString();
        this.name = in.readString();
        this.num = in.readString();
        this.bannned = (Integer) in.readValue(Integer.class.getClassLoader());
        this.lasthit = (Integer) in.readValue(Integer.class.getClassLoader());
        this.op = (Integer) in.readValue(Integer.class.getClassLoader());
        this.email = in.readString();
        this.filesCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.tags = in.readString();
        this.repliesPostList = in.createStringArrayList();
        this.files = in.createTypedArrayList(DataImage.CREATOR);
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
