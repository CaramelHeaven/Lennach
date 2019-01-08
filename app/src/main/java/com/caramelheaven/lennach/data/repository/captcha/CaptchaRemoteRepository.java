package com.caramelheaven.lennach.data.repository.captcha;

import com.caramelheaven.lennach.data.datasource.network.LennachApiService;
import com.caramelheaven.lennach.domain.CaptchaRepository;
import com.caramelheaven.lennach.models.mapper.captcha.CaptchaMapper;
import com.caramelheaven.lennach.models.model.captcha.Captcha;

import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 20:35, 08/01/2019.
 */
public class CaptchaRemoteRepository implements CaptchaRepository {
    private final LennachApiService apiService;
    private final CaptchaMapper captchaMessageMapper;

    public CaptchaRemoteRepository(LennachApiService apiService, CaptchaMapper captchaMessageMapper) {
        this.apiService = apiService;
        this.captchaMessageMapper = captchaMessageMapper;
    }

    @Override
    public Single<Captcha> getCaptcha(String type, String board, String thread) {
        return apiService
                .getCaptcha(type, board, thread)
                .map(captchaMessageMapper::map);
    }
}
