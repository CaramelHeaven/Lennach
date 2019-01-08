package com.caramelheaven.lennach.presentation.get_captcha.presenter;

import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.domain.captcha_use_case.GetCaptcha;
import com.caramelheaven.lennach.models.model.captcha.Captcha;
import com.caramelheaven.lennach.presentation.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by CaramelHeaven on 02:43, 04/01/2019.
 */
public class CaptchaPresenter extends BasePresenter<Captcha, CaptchaView> {

    @Inject
    GetCaptcha getCaptcha;

    public CaptchaPresenter() {
        Lennach.getComponentsManager()
                .plusCaptchaComponent()
                .inject(this);
    }

    @Override
    public void onDestroy() {
        Lennach.getComponentsManager()
                .clearCaptchaComponent();
        super.onDestroy();
    }

    @Override
    protected void handlerError(Throwable throwable) {

    }

    @Override
    protected void successfulResult(Captcha result) {

    }

    @Override
    protected void getData() {

    }
}
