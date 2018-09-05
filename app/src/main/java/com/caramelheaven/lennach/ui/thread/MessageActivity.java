package com.caramelheaven.lennach.ui.thread;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.ui.thread.presenter.MessagePresenter;

import java.util.Map;

public class MessageActivity extends AppCompatActivity {
    EditText msg;
    ImageView captchaImg;
    EditText captchaEdit;
    String captchaId;
    Button postMsg;
    MessagePresenter messagePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity);
        messagePresenter = new MessagePresenter(this);

        Bundle extras = getIntent().getExtras();

        String boardNumber = "b";
        String threadNumber = extras.getString("THREADNUMB");

        msg = (EditText) findViewById(R.id.msg_edit);
        captchaEdit = (EditText) findViewById(R.id.captcha_edit);
        captchaImg = (ImageView) findViewById(R.id.captcha_img);
        postMsg = (Button) findViewById(R.id.postMsg);

        captchaImg.setOnClickListener(view -> {
            messagePresenter.getCaptchaId(boardNumber,threadNumber);
        });

        postMsg.setOnClickListener(view -> {
            String task = "post";
            String board = boardNumber;
            String thread = threadNumber;
            String comment = msg.getText().toString();
            String captcha_type="2chaptcha";
            String captchaValue = captchaEdit.getText().toString();

            messagePresenter.postMessage(task,board,thread,comment,captcha_type,captchaId,captchaValue);
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
