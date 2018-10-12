package com.caramelheaven.lennach.data.repository.captcha;

import com.caramelheaven.lennach.data.datasource.network.LennachApiService;
import com.caramelheaven.lennach.domain.CaptchaRepository;
import com.caramelheaven.lennach.models.mapper.captcha.CaptchaMapper;
import com.caramelheaven.lennach.models.model.common.Captcha;
import com.caramelheaven.lennach.models.model.common.Message;

import java.io.File;

import io.reactivex.Single;

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
                .map(captchaMessageMapper::mapCaptcha);
    }

    @Override
    public Single<Message> getMessage(String board, String thread, String message, String filePath, String captchaId,
                                      String captchaValue) {
        return apiService
                .sendMessage(captchaMessageMapper.mapData(board, thread, message, filePath,captchaId, captchaValue))
                .map(captchaMessageMapper::mapMessage);
    }
}
