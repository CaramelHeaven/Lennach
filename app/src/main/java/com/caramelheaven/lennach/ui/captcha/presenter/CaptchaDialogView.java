package com.caramelheaven.lennach.ui.captcha.presenter;

import com.arellomobile.mvp.MvpView;

public interface CaptchaDialogView extends MvpView {

    void setCaptchaId(String captchaId);

    void setCaptchaImg();

    void postMessage();

    void errorMessage(String message);

    void correctCaptcha();
}
