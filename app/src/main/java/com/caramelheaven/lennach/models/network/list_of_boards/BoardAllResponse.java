package com.caramelheaven.lennach.models.network.list_of_boards;

import com.caramelheaven.lennach.models.network.list_of_boards.network.BoardAllNetwork;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by CaramelHeaven on 18:43, 03/02/2019.
 */
public class BoardAllResponse {

    @SerializedName("Взрослым")
    private BoardAllNetwork[] adults;
    @SerializedName("Игры")
    private BoardAllNetwork[] games;
    @SerializedName("Политика")
    private BoardAllNetwork[] politics;
    @SerializedName("Пользовательские")
    private BoardAllNetwork[] customs;
    @SerializedName("Разное")
    private BoardAllNetwork[] miscellaneas;
    @SerializedName("Творчество")
    private BoardAllNetwork[] creations;
    @SerializedName("Тематика")
    private BoardAllNetwork[] topics;
    @SerializedName("Техника и софт")
    private BoardAllNetwork[] techniqueSoftwares;
    @SerializedName("Японская культура")
    private BoardAllNetwork[] japaneseCultures;

    public List<BoardAllNetwork> sumAllData() {
        List<BoardAllNetwork> boardAllNetworks = new ArrayList<>();

        boardAllNetworks.addAll(Arrays.asList(adults));
        boardAllNetworks.addAll(Arrays.asList(games));
        boardAllNetworks.addAll(Arrays.asList(politics));
        boardAllNetworks.addAll(Arrays.asList(customs));
        boardAllNetworks.addAll(Arrays.asList(miscellaneas));
        boardAllNetworks.addAll(Arrays.asList(creations));
        boardAllNetworks.addAll(Arrays.asList(topics));
        boardAllNetworks.addAll(Arrays.asList(techniqueSoftwares));
        boardAllNetworks.addAll(Arrays.asList(japaneseCultures));

        return boardAllNetworks;
    }

    public BoardAllNetwork[] getAdults() {
        return adults;
    }

    public BoardAllNetwork[] getGames() {
        return games;
    }

    public BoardAllNetwork[] getPolitics() {
        return politics;
    }

    public BoardAllNetwork[] getCustoms() {
        return customs;
    }

    public BoardAllNetwork[] getMiscellaneas() {
        return miscellaneas;
    }

    public BoardAllNetwork[] getCreations() {
        return creations;
    }

    public BoardAllNetwork[] getTopics() {
        return topics;
    }

    public BoardAllNetwork[] getTechniqueSoftwares() {
        return techniqueSoftwares;
    }

    public BoardAllNetwork[] getJapaneseCultures() {
        return japaneseCultures;
    }
}

