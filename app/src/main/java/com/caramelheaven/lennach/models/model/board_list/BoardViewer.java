package com.caramelheaven.lennach.models.model.board_list;

import java.util.Objects;

/**
 * Created by CaramelHeaven on 18:46, 03/02/2019.
 */
public class BoardViewer {
    private String id;
    private String name;
    private String category;

    private boolean selected;

    public BoardViewer(String id, String name, String category) {
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
        BoardViewer that = (BoardViewer) o;
        return selected == that.selected &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, category, selected);
    }
}
