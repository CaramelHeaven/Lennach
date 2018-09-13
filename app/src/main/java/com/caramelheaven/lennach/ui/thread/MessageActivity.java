package com.caramelheaven.lennach.ui.thread;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.ui.thread.presenter.MessagePresenter;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MessageActivity extends AppCompatActivity {
    EditText msg;
    ImageView captchaImg;
    EditText captchaEdit;
    String captchaId;
    Button postMsg;
    MessagePresenter messagePresenter;
    Map<String,RequestBody> options;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity);
        messagePresenter = new MessagePresenter(this);
        options = new HashMap<>();

        Bundle extras = getIntent().getExtras();

        String boardNumber = "b";
        String threadNumber = extras.getString("THREADNUMB");

        msg = (EditText) findViewById(R.id.msg_edit);
        captchaEdit = (EditText) findViewById(R.id.captcha_edit_dialog);
        captchaImg = (ImageView) findViewById(R.id.captcha_img_dialog);
        postMsg = (Button) findViewById(R.id.postMsg);

        captchaImg.setOnClickListener(view -> {
            messagePresenter.getCaptchaId(boardNumber,threadNumber);
        });

        postMsg.setOnClickListener(view -> {

            options.put("board",RequestBody.create(MediaType.parse("text/plain"),boardNumber));
            options.put("thread",RequestBody.create(MediaType.parse("text/plain"),threadNumber));
            options.put("comment",RequestBody.create(MediaType.parse("text/plain"),msg.getText().toString()));
            options.put("captcha_type",RequestBody.create(MediaType.parse("text/plain"),"2chaptcha"));
            options.put("2chaptcha_id",RequestBody.create(MediaType.parse("text/plain"),captchaId));
            options.put("2chaptcha_value",RequestBody.create(MediaType.parse("text/plain"),captchaEdit.getText().toString()));

            messagePresenter.postMessage(options);
        });
    }

    public void setCaptchaId(String capthacaId) {
        this.captchaId = capthacaId;
        setCaptchaImg();

    }

    public void setCaptchaImg() {
        Glide.with(this)
                .load("https://2ch.hk/api/captcha/2chaptcha/image/"+captchaId)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(captchaImg);
    }
}
