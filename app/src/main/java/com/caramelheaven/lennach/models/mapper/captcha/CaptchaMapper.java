package com.caramelheaven.lennach.models.mapper.captcha;

import com.caramelheaven.lennach.models.model.captcha.Captcha;
import com.caramelheaven.lennach.models.network.CaptchaResponse;

/**
 * Created by CaramelHeaven on 20:23, 08/01/2019.
 */
public class CaptchaMapper {
    private final CaptchaResponseToCaptcha captchaResponceToCaptcha;

    public CaptchaMapper(CaptchaResponseToCaptcha captchaResponseToCaptcha) {
        this.captchaResponceToCaptcha = captchaResponseToCaptcha;
    }

    public Captcha map(CaptchaResponse response) {
        return captchaResponceToCaptcha.map(response);
    }
}
