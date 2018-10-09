package com.caramelheaven.lennach.presentation.captcha.presenter;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface CaptchaDialogView extends MvpView {

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void errorMessage(int errorCode);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void correctCaptcha();

    void showImage();
}
