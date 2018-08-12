package com.caramelheaven.lennach.datasource.model;

public class BoardNavModel {
    private String name;

    public BoardNavModel(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "BoardNavModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
