package com.caramelheaven.lennach.domain.captcha_use_case;

import com.caramelheaven.lennach.domain.CaptchaRepository;
import com.caramelheaven.lennach.models.model.captcha.Captcha;
import com.caramelheaven.lennach.utils.Constants;

import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 02:44, 04/01/2019.
 */
public class GetCaptcha {
    private final CaptchaRepository captchaRepository;

    public GetCaptcha(CaptchaRepository captchaRepository) {
        this.captchaRepository = captchaRepository;
    }

    public Single<Captcha> createUseCase(String boardName, String threadId) {
        return captchaRepository.getCaptcha(Constants.INSTANCE.getCAPTCHA_TYPE(), boardName, threadId);
    }
}
