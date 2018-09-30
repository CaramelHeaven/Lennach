package com.caramelheaven.lennach.di.captcha;

import com.caramelheaven.lennach.presentation.captcha.presenter.CaptchaPresenter;

import dagger.Subcomponent;

@CaptchaScope
@Subcomponent(modules = CaptchaModule.class)
public interface CaptchaComponent {

    void inject(CaptchaPresenter captchaPresenter);
}
