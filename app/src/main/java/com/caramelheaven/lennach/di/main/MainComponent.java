package com.caramelheaven.lennach.di.main;

import com.caramelheaven.lennach.di.board.BoardComponent;
import com.caramelheaven.lennach.di.board.BoardModule;
import com.caramelheaven.lennach.di.captcha.CaptchaComponent;
import com.caramelheaven.lennach.di.captcha.CaptchaModule;
import com.caramelheaven.lennach.di.thread.ThreadComponent;
import com.caramelheaven.lennach.di.thread.ThreadModule;
import com.caramelheaven.lennach.presentation.main.presenter.MainPresenter;

import dagger.Subcomponent;

@MainScope
@Subcomponent(modules = {MainModule.class})
public interface MainComponent {

    BoardComponent plusBoardComponent(BoardModule module);

    ThreadComponent plusThreadComponent(ThreadModule module);

    CaptchaComponent plusCaptchaComponent(CaptchaModule captchaModule);

    void inject(MainPresenter mainPresenter);
}
