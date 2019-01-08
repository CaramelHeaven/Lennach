package com.caramelheaven.lennach.di.captcha;

import com.caramelheaven.lennach.data.datasource.network.LennachApiService;
import com.caramelheaven.lennach.data.repository.captcha.CaptchaRemoteRepository;
import com.caramelheaven.lennach.domain.CaptchaRepository;
import com.caramelheaven.lennach.domain.captcha_use_case.GetCaptcha;
import com.caramelheaven.lennach.models.mapper.captcha.CaptchaMapper;
import com.caramelheaven.lennach.models.mapper.captcha.CaptchaResponseToCaptcha;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CaramelHeaven on 20:21, 08/01/2019.
 */
@Module
public class CaptchaModule {

    @CaptchaScope
    @Provides
    GetCaptcha provideGetCaptcha(CaptchaRepository captchaRepository) {
        return new GetCaptcha(captchaRepository);
    }

    @CaptchaScope
    @Provides
    CaptchaRepository provideCaptchaRepository(LennachApiService apiService,
                                               CaptchaMapper captchaMapper) {
        return new CaptchaRemoteRepository(apiService, captchaMapper);
    }

    @CaptchaScope
    @Provides
    CaptchaMapper provideCaptchaMapper(CaptchaResponseToCaptcha captchaResponseToCaptcha) {
        return new CaptchaMapper(captchaResponseToCaptcha);
    }

    @CaptchaScope
    @Provides
    CaptchaResponseToCaptcha provideCaptchaResponseToCaptcha() {
        return new CaptchaResponseToCaptcha();
    }
}
