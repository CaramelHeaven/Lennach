package com.caramelheaven.lennach.datasource.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.caramelheaven.lennach.datasource.database.entity.iFile;

import java.util.List;

@Dao
public interface FileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFiles(List<iFile> iFiles);

    @Update
    void updatePost(iFile iFile);

    @Delete
    void deletePost(iFile iFile);

    @Query("SELECT * FROM iFile")
    List<iFile> getAllPosts();

    @Query("SELECT * FROM iFile WHERE idPost=:postId")
    iFile getFileById(String postId);
}
