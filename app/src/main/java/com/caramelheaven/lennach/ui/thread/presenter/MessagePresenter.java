package com.caramelheaven.lennach.ui.thread.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.datasource.database.LennachDatabase;
import com.caramelheaven.lennach.datasource.network.ApiService;
import com.caramelheaven.lennach.ui.thread.MessageActivity;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MessagePresenter extends MvpPresenter<MessageView> {
    private CompositeDisposable disposable;
    private MessageActivity view;

    @Inject
    ApiService apiService;

    public MessagePresenter(MessageActivity view) {
        disposable = new CompositeDisposable();
        this.view = view;
        Lennach.getComponent().injectMessagePresenter(this);
    }

    public void getCaptchaId(String boardNumber, String threadNumber) {
        disposable.add(apiService.getCaptcha("2chaptcha",boardNumber,threadNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(captcha -> {
                    view.setCaptchaId(captcha.getId());
                }));
    }

    public void postMessage(Map<String,String > options) {
        disposable.add(apiService.sendPostInThread(options)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(postInThread -> {
                    System.out.println(postInThread.getStatus());
                }));
    }


}
