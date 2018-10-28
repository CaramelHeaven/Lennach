package com.caramelheaven.lennach.data.repository.captcha;

import com.caramelheaven.lennach.data.datasource.network.LennachApiService;
import com.caramelheaven.lennach.domain.CaptchaRepository;
import com.caramelheaven.lennach.models.mapper.captcha.CaptchaMapper;
import com.caramelheaven.lennach.models.model.common.Captcha;
import com.caramelheaven.lennach.models.model.common.Message;
import com.caramelheaven.lennach.models.network.MessagePostResponse;

import java.io.File;
import java.util.Map;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

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
        if (filePath != null) {
            return apiService.sendMessageWithImage(captchaMessageMapper.mapTest(board, thread, message, captchaId, captchaValue),
                    captchaMessageMapper.mapPhoto(filePath))
                    .map(captchaMessageMapper::mapMessage);
        } else {
            return apiService.sendMessage(captchaMessageMapper.mapTest(board, thread, message, captchaId, captchaValue))
                    .map(captchaMessageMapper::mapMessage);
        }
    }
}
