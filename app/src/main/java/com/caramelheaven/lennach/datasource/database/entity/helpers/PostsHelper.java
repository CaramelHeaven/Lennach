package com.caramelheaven.lennach.datasource.database.entity.helpers;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;
import android.os.Parcel;
import android.os.Parcelable;

import com.caramelheaven.lennach.datasource.database.entity.iFile;
import com.caramelheaven.lennach.datasource.database.entity.iPost;

import java.util.ArrayList;
import java.util.List;

public class PostsHelper implements Parcelable {
    @Embedded
    public iPost iPost;

    @Relation(entity = iFile.class, parentColumn = "postId", entityColumn = "idPost")
    public List<iFile> iFileList;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.iPost, flags);
        dest.writeList(this.iFileList);
    }

    public PostsHelper() {
    }

    protected PostsHelper(Parcel in) {
        this.iPost = in.readParcelable(com.caramelheaven.lennach.datasource.database.entity.iPost.class.getClassLoader());
        this.iFileList = new ArrayList<iFile>();
        in.readList(this.iFileList, iFile.class.getClassLoader());
    }

    public static final Parcelable.Creator<PostsHelper> CREATOR = new Parcelable.Creator<PostsHelper>() {
        @Override
        public PostsHelper createFromParcel(Parcel source) {
            return new PostsHelper(source);
        }

        @Override
        public PostsHelper[] newArray(int size) {
            return new PostsHelper[size];
        }
    };
}
