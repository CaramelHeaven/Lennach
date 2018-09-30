package com.caramelheaven.lennach.domain;

import com.caramelheaven.lennach.models.model.common.Captcha;
import com.caramelheaven.lennach.models.model.common.Message;

import io.reactivex.Single;

public interface CaptchaRepository {
    Single<Captcha> getCaptcha(String type, String board, String thread);

    Single<Message> getMessage(String board, String thread, String message, String captchaId,
                               String captchaValue);
}
