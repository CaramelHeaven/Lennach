package com.caramelheaven.lennach.domain.captcha_user_case;

import com.caramelheaven.lennach.domain.CaptchaRepository;
import com.caramelheaven.lennach.models.model.common.Message;

import io.reactivex.Single;

public class GetMessage {

    private final CaptchaRepository captchaRepository;

    public GetMessage(CaptchaRepository captchaRepository) {
        this.captchaRepository = captchaRepository;
    }

    public Single<Message> createUseCase(String board, String thread, String message,
                                         String captchaId, String captchaValue) {
        return captchaRepository
                .getMessage(board, thread, message, captchaId, captchaValue);
    }
}
