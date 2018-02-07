package com.caramelheaven.lennach.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lion on 07.02.2018.
 */

public class SimplyBoard {
    @SerializedName("threads")
    @Expose
    private List<ThreadMy> threads;

    public List<ThreadMy> getThreads() {
        return threads;
    }
}
