package com.caramelheaven.lennach.di.captcha;

import com.caramelheaven.lennach.presentation.get_captcha.presenter.CaptchaPresenter;

import dagger.Subcomponent;

/**
 * Created by CaramelHeaven on 20:21, 08/01/2019.
 */
@CaptchaScope
@Subcomponent(modules = CaptchaModule.class)
public interface CaptchaComponent {
    void inject(CaptchaPresenter captchaPresenter);
}
