package com.caramelheaven.lennach.di;

import com.caramelheaven.lennach.datasource.di.DaggerAppComponent;
import com.caramelheaven.lennach.di.application.AppComponent;
import com.caramelheaven.lennach.di.board.BoardComponent;
import com.caramelheaven.lennach.di.board.BoardModule;

public class ComponentsManager {
    private AppComponent appComponent;
    private BoardComponent boardComponent;

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.create();
        }
        return appComponent;
    }

    public BoardComponent getBoardComponent() {
        if (boardComponent == null) {
            boardComponent = appComponent.plusBoardComponent(new BoardModule());
        }
        return boardComponent;
    }
}
