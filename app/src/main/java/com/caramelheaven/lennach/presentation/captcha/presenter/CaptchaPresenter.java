package com.caramelheaven.lennach.presentation.captcha.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.domain.captcha_user_case.GetCaptcha;
import com.caramelheaven.lennach.domain.captcha_user_case.GetMessage;
import com.caramelheaven.lennach.models.model.common.Captcha;
import com.caramelheaven.lennach.models.model.common.Message;
import com.caramelheaven.lennach.utils.Constants;

import java.io.File;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import timber.log.Timber;

@InjectViewState
public class CaptchaPresenter extends MvpPresenter<CaptchaDialogView> {

    private CompositeDisposable disposable;
    private String board, thread, message, captchaId;
    private String filePath;

    @Inject
    GetCaptcha getCaptcha;

    @Inject
    GetMessage getResult;

    public CaptchaPresenter(String board, String thread, String message, String filePath) {
        this.board = board;
        this.thread = thread;
        this.message = message;
        this.filePath = filePath;
        Lennach.getComponentsManager()
                .plusCaptchaComponent()
                .inject(this);
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadCaptcha();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
        Lennach.getComponentsManager()
                .clearCaptchaComponent();
    }

    public void loadCaptcha() {
        disposable.add(getCaptcha.createUseCase(board, thread)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulLoad, this::unsuccessfulLoad));
    }

    private void successfulLoad(Captcha captcha) {
        setCaptchaId(captcha.getId());
        getViewState().showImage();
    }

    private void unsuccessfulLoad(Throwable throwable) {
        Timber.d("unsuccessful throwable: " + throwable.getMessage());
        Timber.d("unsuccessful throwable: " + throwable.getCause());
    }

    public void postMessage(String captchaValue) {
        disposable.add(getResult.createUseCase(board, thread, message,filePath, captchaId, captchaValue)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::resultPostMessage, this::resultErrorPostMessage));
    }

    private void resultPostMessage(Message message) {
        if (message.getStatus() != null) {
            getViewState().correctCaptcha();
        } else {
            getViewState().errorMessage(message.getError());
        }
    }

    private void resultErrorPostMessage(Throwable throwable) {
        Timber.d("throwabel: " + throwable.getCause());
        Timber.d("throwabel: " + throwable.getMessage());
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public String getCaptchaId() {
        return captchaId;
    }
}
