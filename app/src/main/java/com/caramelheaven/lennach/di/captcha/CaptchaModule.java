package com.caramelheaven.lennach.di.captcha;

import com.caramelheaven.lennach.data.datasource.network.LennachApiService;
import com.caramelheaven.lennach.data.repository.captcha.CaptchaRemoteRepository;
import com.caramelheaven.lennach.di.ActivityScope;
import com.caramelheaven.lennach.domain.CaptchaRepository;
import com.caramelheaven.lennach.domain.captcha_user_case.GetCaptcha;
import com.caramelheaven.lennach.domain.captcha_user_case.GetMessage;
import com.caramelheaven.lennach.models.mapper.captcha.CaptchaMapper;
import com.caramelheaven.lennach.models.mapper.captcha.CaptchaResponseToCaptcha;
import com.caramelheaven.lennach.models.mapper.captcha.MessagePostResponseToMessage;

import dagger.Module;
import dagger.Provides;

@Module
public class CaptchaModule {

    @CaptchaScope
    @Provides
    GetCaptcha provideGetThread(CaptchaRepository captchaRepository) {
        return new GetCaptcha(captchaRepository);
    }

    @CaptchaScope
    @Provides
    GetMessage provideGetMessage(CaptchaRepository captchaRepository) {
        return new GetMessage(captchaRepository);
    }

    @CaptchaScope
    @Provides
    CaptchaRepository provideCaptchaRepository(LennachApiService apiService,
                                               CaptchaMapper captchaMapper) {
        return new CaptchaRemoteRepository(apiService, captchaMapper);
    }

    @CaptchaScope
    @Provides
    CaptchaMapper provideCaptchaMapper(CaptchaResponseToCaptcha captchaResponseToCaptcha,
                                       MessagePostResponseToMessage messagePostResponseToMessage) {
        return new CaptchaMapper(captchaResponseToCaptcha, messagePostResponseToMessage);
    }

    @CaptchaScope
    @Provides
    CaptchaResponseToCaptcha provideCaptchaResponseToCaptcha() {
        return new CaptchaResponseToCaptcha();
    }

    @CaptchaScope
    @Provides
    MessagePostResponseToMessage provideMessagePostResponseToMessage() {
        return new MessagePostResponseToMessage();
    }
}
