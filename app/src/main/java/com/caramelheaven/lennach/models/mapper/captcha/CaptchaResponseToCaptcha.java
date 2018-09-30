package com.caramelheaven.lennach.models.mapper.captcha;

import com.caramelheaven.lennach.models.mapper.Mapper;
import com.caramelheaven.lennach.models.model.common.Captcha;
import com.caramelheaven.lennach.models.network.CaptchaResponse;

public class CaptchaResponseToCaptcha extends Mapper<CaptchaResponse, Captcha> {

    @Override
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
