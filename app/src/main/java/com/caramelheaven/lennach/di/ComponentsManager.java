package com.caramelheaven.lennach.di;

import android.content.Context;

import com.caramelheaven.lennach.di.application.AppComponent;
import com.caramelheaven.lennach.di.application.AppModule;
import com.caramelheaven.lennach.di.application.DaggerAppComponent;
import com.caramelheaven.lennach.di.board.BoardComponent;
import com.caramelheaven.lennach.di.board.BoardModule;
import com.caramelheaven.lennach.di.captcha.CaptchaComponent;
import com.caramelheaven.lennach.di.captcha.CaptchaModule;
import com.caramelheaven.lennach.di.main.MainComponent;
import com.caramelheaven.lennach.di.main.MainModule;
import com.caramelheaven.lennach.di.thread.ThreadComponent;
import com.caramelheaven.lennach.di.thread.ThreadModule;

public class ComponentsManager {

    private AppComponent appComponent;
    private MainComponent mainComponent;
    private BoardComponent boardComponent;
    private ThreadComponent threadComponent;
    private CaptchaComponent captchaComponent;

    private Context context;

    public ComponentsManager(Context context) {
        this.context = context;
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(context))
                    .build();
        }
        return appComponent;
    }

    public MainComponent plusMainComponent() {
        if (mainComponent == null) {
            mainComponent = appComponent.plusMainComponent(new MainModule());
        }
        return mainComponent;
    }

    public BoardComponent plusBoardComponent() {
        if (boardComponent == null) {
            boardComponent = mainComponent.plusBoardComponent(new BoardModule());
        }
        return boardComponent;
    }

    public ThreadComponent plusThreadComponent() {
        if (threadComponent == null) {
            threadComponent = mainComponent.plusThreadComponent(new ThreadModule());
        }
        return threadComponent;
    }

    public CaptchaComponent plusCaptchaComponent() {
        if (captchaComponent == null) {
            captchaComponent = mainComponent.plusCaptchaComponent(new CaptchaModule());
        }
        return captchaComponent;
    }

    public void clearMainComponent() {
        mainComponent = null;
    }

    public void clearBoardComponent() {
        boardComponent = null;
    }

    public void clearThreadComponent() {
        threadComponent = null;
    }

    public void clearCaptchaComponent() {
        captchaComponent = null;
    }
}