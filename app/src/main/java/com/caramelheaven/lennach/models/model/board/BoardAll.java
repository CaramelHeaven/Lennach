package com.caramelheaven.lennach.models.model.board;

import java.util.Objects;

/**
 * Created by CaramelHeaven on 21:20, 20/01/2019.
 */
public class BoardAll {
    private String id;
    private String name;
    private String category;

    private boolean selected;

    public BoardAll(String id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "BoardAll{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", selected=" + selected +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardAll boardAll = (BoardAll) o;
        return selected == boardAll.selected &&
                Objects.equals(id, boardAll.id) &&
                Objects.equals(name, boardAll.name) &&
                Objects.equals(category, boardAll.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, selected);
    }
}
