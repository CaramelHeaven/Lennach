package com.caramelheaven.lennach.models.mapper.captcha;

import com.caramelheaven.lennach.models.mapper.Mapper;
import com.caramelheaven.lennach.models.model.common.Message;
import com.caramelheaven.lennach.models.network.MessagePostResponse;
import com.caramelheaven.lennach.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MessagePostResponseToMessage extends Mapper<MessagePostResponse, Message> {
    @Override
    public Message map(MessagePostResponse value) {
        Message message = new Message();
        fillMessage(message, value);
        return message;
    }

    public Map<String, RequestBody> map(String board, String thread, String message,
                                        String captchaId, String captchaValue) {
        Map<String, RequestBody> data = new HashMap<>();
        fillData(data, board, thread, message, captchaId, captchaValue);
        return data;
    }

    private void fillMessage(Message message, MessagePostResponse response) {
        message.setError(response.getError());
        message.setNum(response.getNum());
        message.setStatus(response.getStatus());
    }

    private void fillData(Map<String, RequestBody> data, String board, String thread, String message,
                          String captchaId, String captchaValue) {
        data.put("board", RequestBody.create(MediaType.parse("text/plain"), board));
        data.put("thread", RequestBody.create(MediaType.parse("text/plain"), thread));
        data.put("comment", RequestBody.create(MediaType.parse("text/plain"), message));
        data.put("captcha_type", RequestBody.create(MediaType.parse("text/plain"), Constants.CAPTCHA_TYPE));
        data.put("2chaptcha_id", RequestBody.create(MediaType.parse("text/plain"), captchaId));
        data.put("2chaptcha_value", RequestBody.create(MediaType.parse("text/plain"), captchaValue));
    }
}
