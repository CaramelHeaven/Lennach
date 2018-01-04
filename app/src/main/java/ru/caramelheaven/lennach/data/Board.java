package ru.caramelheaven.lennach.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import ru.caramelheaven.lennach.database.ThreadViewDB;

/**
 * Created by Sergey F on 02.01.2018.
 */

public class Board {
    @SerializedName("threads")
    @Expose
    private RealmList<ThreadViewDB> threads;

    public RealmList<ThreadViewDB> getThreads() {
        return threads;
    }
}