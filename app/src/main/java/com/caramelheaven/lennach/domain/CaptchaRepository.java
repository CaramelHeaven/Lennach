package com.caramelheaven.lennach.domain;

import com.caramelheaven.lennach.models.model.captcha.Captcha;

import io.reactivex.Single;

public interface CaptchaRepository {
    Single<Captcha> getCaptcha(String type, String board, String thread);
}
