package com.caramelheaven.lennach.ui.thread;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.ui.thread.presenter.MessagePresenter;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CaptchaDialog extends DialogFragment{

    ImageView captchaImg;
    EditText captchaEdit;
    Button postMsg;
    Button cancel;
    MessagePresenter messagePresenter;
    String msg;
    String threadNumber;
    String boardNumber;
    String captchaId;
    Map<String,RequestBody> options;

    public CaptchaDialog() {

    }

    public static CaptchaDialog newInstance(String title) {
        CaptchaDialog frag = new CaptchaDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.captcha_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        messagePresenter = new MessagePresenter(this);
        options = new HashMap<String,RequestBody>();


        boardNumber = "b";
        threadNumber = getArguments().getString("THREADNUMB");;
        msg = getArguments().getString("MESSAGE");

        System.out.println("!!!!!!!!!!!!!!");
        System.out.println(msg);
        System.out.println(threadNumber);
        System.out.println("!!!!!!!!!!!!!!");

        captchaImg = (ImageView) view.findViewById(R.id.captcha_img_dialog);
        captchaEdit = (EditText) view.findViewById(R.id.captcha_edit_dialog);
        postMsg = (Button) view.findViewById(R.id.postMsg_dialog);
        cancel = (Button) view.findViewById(R.id.cancel_dialog);

        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);

        // Show soft keyboard automatically and request focus to field
        captchaEdit.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        captchaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messagePresenter.getCaptchaId(boardNumber,threadNumber);
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
                if(editable.length() == 6) {
                    postMessage();
                }
            }
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

    public void postMessage() {
        options.put("board", RequestBody.create(MediaType.parse("text/plain"),boardNumber));
        options.put("thread",RequestBody.create(MediaType.parse("text/plain"),threadNumber));
        options.put("comment",RequestBody.create(MediaType.parse("text/plain"),msg));
        options.put("captcha_type",RequestBody.create(MediaType.parse("text/plain"),"2chaptcha"));
        options.put("2chaptcha_id",RequestBody.create(MediaType.parse("text/plain"),captchaId));
        options.put("2chaptcha_value",RequestBody.create(MediaType.parse("text/plain"),captchaEdit.getText().toString()));

        messagePresenter.postMessage(options);
    }

    public void errorMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void correctCaptcha() {
        //Update Thread
    }

}
