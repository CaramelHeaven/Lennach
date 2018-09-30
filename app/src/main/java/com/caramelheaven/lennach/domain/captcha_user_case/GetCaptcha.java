package com.caramelheaven.lennach.domain.captcha_user_case;

import com.caramelheaven.lennach.domain.CaptchaRepository;
import com.caramelheaven.lennach.models.model.common.Captcha;
import com.caramelheaven.lennach.utils.Constants;

import io.reactivex.Single;

public class GetCaptcha {
    private final CaptchaRepository captchaRepository;

    public GetCaptcha(CaptchaRepository captchaRepository) {
        this.captchaRepository = captchaRepository;
    }

    public Single<Captcha> createUseCase(String boardName, String threadId) {
        return captchaRepository.getCaptcha(Constants.CAPTCHA_TYPE, boardName, threadId);
    }
}
