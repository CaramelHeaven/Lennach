package com.caramelheaven.lennach.utils.bus;

import android.view.View;

import com.caramelheaven.lennach.models.model.common.DataImage;

import java.util.ArrayList;

public class Kek {
    private String kek;
    private View view;
    private int pos;
    private int currentPos;
    private ArrayList<DataImage> dataImages;

    public Kek(String kek, View view, int pos, int currentPos, ArrayList<DataImage> dataImages) {
        this.kek = kek;
        this.view = view;
        this.pos = pos;
        this.currentPos = currentPos;
        this.dataImages = dataImages;
    }

    public String getKek() {
        return kek;
    }

    public View getView() {
        return view;
    }

    public int getPos() {
        return pos;
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public ArrayList<DataImage> getDataImages() {
        return dataImages;
    }
}
