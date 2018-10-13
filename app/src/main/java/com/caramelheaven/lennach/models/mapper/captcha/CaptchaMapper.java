package com.caramelheaven.lennach.models.mapper.captcha;

import com.caramelheaven.lennach.models.model.common.Captcha;
import com.caramelheaven.lennach.models.model.common.Message;
import com.caramelheaven.lennach.models.network.CaptchaResponse;
import com.caramelheaven.lennach.models.network.MessagePostResponse;

import java.io.File;
import java.util.Map;

import okhttp3.RequestBody;

public class CaptchaMapper {
    private CaptchaResponseToCaptcha captchaResponseToCaptcha;
    private MessagePostResponseToMessage messagePostResponseToMessage;

    public CaptchaMapper(CaptchaResponseToCaptcha captchaResponseToCaptcha,
                         MessagePostResponseToMessage messagePostResponseToMessage) {
        this.captchaResponseToCaptcha = captchaResponseToCaptcha;
        this.messagePostResponseToMessage = messagePostResponseToMessage;
    }

    public Captcha mapCaptcha(CaptchaResponse response) {
        return captchaResponseToCaptcha.map(response);
    }

    public Message mapMessage(MessagePostResponse response) {
        return messagePostResponseToMessage.map(response);
    }

    public Map<String, RequestBody> mapData(String board, String thread, String message, String filePath,
                                            String captchaId, String captchaValue) {
        return messagePostResponseToMessage.map(board, thread, message, filePath, captchaId, captchaValue);
    }
}
