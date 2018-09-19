package com.caramelheaven.lennach.ui.captcha;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.ui.captcha.presenter.CaptchaDialogView;
import com.caramelheaven.lennach.ui.thread.SendMessageListener;
import com.caramelheaven.lennach.ui.captcha.presenter.CaptchaPresenter;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CaptchaDialogFragment extends DialogFragment implements CaptchaDialogView {

    ImageView captchaImg;
    EditText captchaEdit;
    CaptchaPresenter captchaPresenter;
    String msg;
    String threadNumber;
    String boardNumber;
    String captchaId;
    Map<String, RequestBody> options;

    public CaptchaDialogFragment() {

    }

    public static CaptchaDialogFragment newInstance(String threadNumber, String msg) {
        CaptchaDialogFragment fragment = new CaptchaDialogFragment();
        Bundle args = new Bundle();
        args.putString("THREADNUMB", threadNumber);
        args.putString("MESSAGE", msg);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.captcha_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        captchaPresenter = new CaptchaPresenter(this);
        options = new HashMap<String, RequestBody>();

        boardNumber = "b";
        threadNumber = getArguments().getString("THREADNUMB");
        msg = getArguments().getString("MESSAGE");

        captchaImg = (ImageView) view.findViewById(R.id.captcha_img_dialog);
        captchaEdit = (EditText) view.findViewById(R.id.captcha_edit_dialog);

        captchaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captchaPresenter.getCaptchaId(boardNumber, threadNumber);
            }
        });

        captchaEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 6) {
                    postMessage();
                }
            }
        });

    }

    @Override
    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
        setCaptchaImg();

    }

    @Override
    public void setCaptchaImg() {
        Glide.with(this)
                .load("https://2ch.hk/api/captcha/2chaptcha/image/" + captchaId)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(captchaImg);
    }

    @Override
    public void postMessage() {
        options.put("board", RequestBody.create(MediaType.parse("text/plain"), boardNumber));
        options.put("thread", RequestBody.create(MediaType.parse("text/plain"), threadNumber));
        options.put("comment", RequestBody.create(MediaType.parse("text/plain"), msg));
        options.put("captcha_type", RequestBody.create(MediaType.parse("text/plain"), "2chaptcha"));
        options.put("2chaptcha_id", RequestBody.create(MediaType.parse("text/plain"), captchaId));
        options.put("2chaptcha_value", RequestBody.create(MediaType.parse("text/plain"), captchaEdit.getText().toString()));

        captchaPresenter.postMessage(options);
    }

    @Override
    public void errorMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void correctCaptcha() {
        Toast.makeText(getActivity(), "Сообщение отправлено!", Toast.LENGTH_SHORT).show();
        SendMessageListener threadView = (SendMessageListener) getTargetFragment();
        dismiss();
        threadView.updateThread();
    }

}
