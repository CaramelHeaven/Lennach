package com.caramelheaven.lennach.ui.thread.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.datasource.database.LennachDatabase;
import com.caramelheaven.lennach.datasource.network.ApiService;
import com.caramelheaven.lennach.ui.thread.MessageActivity;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MessagePresenter extends MvpPresenter<MessageView> {
    private CompositeDisposable disposable;
    private MessageActivity view;

    @Inject
    ApiService apiService;

    @Inject
    LennachDatabase database;

    public MessagePresenter(MessageActivity view) {
        disposable = new CompositeDisposable();
        this.view = view;
        Lennach.getComponent().injectMessagePresenter(this);
    }

    public void getCaptchaId(String boardNumber, String threadNumber) {
        /*      apiService.getCaptcha("2chaptcha",boardNumber,threadNumber);*/
        //setCaptchaId
        disposable.add(apiService.getCaptcha("2chaptcha",boardNumber,threadNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(captcha -> {
                    view.setCaptchaId(captcha.getId());
                }));
    }

    public void getCaptchaImgById(String capthacaId) {
        /*apiService.getCaptchaImg(capthacaId);*/
        disposable.add(apiService.getCaptchaImg(capthacaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(img -> {
                    System.out.println("!!!!!!!!!!");
                    System.out.println(img);
                }));
    }

    public void postMessage(String task,
                            String board,
                            String thread,
                            String comment,
                            String captcha_type,
                            String captchaId,
                            String captchaValue) {
        disposable.add(apiService.sendPostInThread(task,board,thread,comment,captcha_type,captchaId,captchaValue)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(postInThread -> {
                    System.out.println(postInThread.getStatus());
                }));
    }


}
