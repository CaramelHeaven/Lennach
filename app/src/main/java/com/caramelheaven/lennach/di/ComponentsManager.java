package com.caramelheaven.lennach.di;

import com.caramelheaven.lennach.di.application.AppComponent;
import com.caramelheaven.lennach.di.application.DaggerAppComponent;
import com.caramelheaven.lennach.di.board.BoardComponent;
import com.caramelheaven.lennach.di.board.BoardModule;
import com.caramelheaven.lennach.di.thread.ThreadComponent;
import com.caramelheaven.lennach.di.thread.ThreadModule;

public class ComponentsManager {
    private AppComponent appComponent;
    private BoardComponent boardComponent;
    private ThreadComponent threadComponent;

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.create();
        }
        return appComponent;
    }

    public BoardComponent plusBoardComponent() {
        if (boardComponent == null) {
            boardComponent = appComponent.plusBoardComponent(new BoardModule());
        }
        return boardComponent;
    }

    public ThreadComponent plusThreadComponent() {
        if (threadComponent == null) {
            threadComponent = appComponent.plusThreadComponent(new ThreadModule());
        }
        return threadComponent;
    }

    public void clearBoardComponent() {
        boardComponent = null;
    }
}
