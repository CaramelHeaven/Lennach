package com.caramelheaven.lennach.models.mapper.captcha;

import com.caramelheaven.lennach.models.model.captcha.Captcha;
import com.caramelheaven.lennach.models.network.CaptchaResponse;

/**
 * Created by CaramelHeaven on 20:23, 08/01/2019.
 */
public class CaptchaResponseToCaptcha {
    public Captcha map(CaptchaResponse value) {
        Captcha captcha = new Captcha();
        fillCaptcha(captcha, value);
        return captcha;
    }

    private void fillCaptcha(Captcha captcha, CaptchaResponse response) {
        captcha.setId(response.getId());
        captcha.setResult(response.getResult());
        captcha.setType(response.getType());
    }
}
