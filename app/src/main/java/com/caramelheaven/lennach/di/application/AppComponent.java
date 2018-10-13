package com.caramelheaven.lennach.di.application;

import com.caramelheaven.lennach.di.board.BoardComponent;
import com.caramelheaven.lennach.di.board.BoardModule;
import com.caramelheaven.lennach.di.captcha.CaptchaComponent;
import com.caramelheaven.lennach.di.captcha.CaptchaModule;
import com.caramelheaven.lennach.di.thread.ThreadComponent;
import com.caramelheaven.lennach.di.thread.ThreadModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {

    BoardComponent plusBoardComponent(BoardModule boardModule);

    ThreadComponent plusThreadComponent(ThreadModule threadModule);

    CaptchaComponent plusCaptchaComponent(CaptchaModule captchaModule);
}
