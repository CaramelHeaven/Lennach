package com.caramelheaven.lennach.models.network.all_board_models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 21:14, 20/01/2019.
 */
public class BoardAllNetwork {
    @SerializedName("bump_limit")
    private int bumpLimit;
    @SerializedName("category")
    private String category;
    @SerializedName("default_name")
    private String defaultName;
    @SerializedName("enable_dices")
    private int enableDices;
    @SerializedName("enable_flags")
    private int enableFlags;
    @SerializedName("enable_icons")
    private int enableIcons;
    @SerializedName("enable_likes")
    private int enableLikes;
    @SerializedName("enable_names")
    private int enableNames;
    @SerializedName("enable_oekaki")
    private int enableOekaki;
    @SerializedName("enable_posting")
    private int enablePosting;
    @SerializedName("enable_sage")
    private int enableSage;
    @SerializedName("enable_shield")
    private int enableShield;
    @SerializedName("enable_subject")
    private int enableSubject;
    @SerializedName("enable_thread_tags")
    private int enableThreadTags;
    @SerializedName("enable_trips")
    private int enableTrips;
    @SerializedName("icons")
    private Object[] icons;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("pages")
    private int pages;
    @SerializedName("sage")
    private int sage;
    @SerializedName("tripcodes")
    private int tripcodes;
}
