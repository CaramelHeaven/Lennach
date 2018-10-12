package com.caramelheaven.lennach.presentation.captcha;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.captcha.presenter.CaptchaDialogView;
import com.caramelheaven.lennach.presentation.captcha.presenter.CaptchaPresenter;
import com.caramelheaven.lennach.utils.Constants;

import java.io.File;
import java.io.Serializable;

public class CaptchaDialogFragment extends MvpAppCompatDialogFragment implements CaptchaDialogView {

    private ImageView captchaImg;
    private EditText etCaptcha;
    private TextInputLayout tilContainer;

    @InjectPresenter
    CaptchaPresenter presenter;

    @ProvidePresenter
    CaptchaPresenter provideCaptchaPresenter() {
        return new CaptchaPresenter(getArguments().getString("BOARD_NAME"),
                getArguments().getString("THREAD_ID"),
                getArguments().getString("MESSAGE"),
                getArguments().getString("FILE_PATH"));
    }

    public static CaptchaDialogFragment newInstance(String board, String threadNumber, String msg, String filePath) {
        CaptchaDialogFragment fragment = new CaptchaDialogFragment();
        Bundle args = new Bundle();
        args.putString("BOARD_NAME", board);
        args.putString("THREAD_ID", threadNumber);
        args.putString("MESSAGE", msg);
        if(filePath!= null) {
            args.putString("FILE_PATH", filePath);
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_captcha, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        captchaImg = view.findViewById(R.id.iv_captcha_image);
        etCaptcha = view.findViewById(R.id.et_captcha);
        tilContainer = view.findViewById(R.id.text_input_container);

        captchaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadCaptcha();
            }
        });

        provideEditText();
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.fragment_dialog_rounded);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        etCaptcha = null;
        captchaImg = null;
        tilContainer = null;
    }

    @Override
    public void errorMessage(int error) {
        final Animation animationShake = AnimationUtils
                .loadAnimation(getActivity(), R.anim.animation_shake);
        switch (error) {
            case Constants.POSTING_IS_FORBIDDEN:
                tilContainer.startAnimation(animationShake);
                showToastError("Постинг запрещен");
                break;
            case Constants.CAPTCHA_IS_WRONG:
                tilContainer.startAnimation(animationShake);
                break;
            case Constants.NOTHING_WRITTEN:
                tilContainer.startAnimation(animationShake);
                showToastError("Вы ничего не запостили");
                break;
            case Constants.TOO_FAST_PUBLICATION:
                tilContainer.startAnimation(animationShake);
                showToastError("Вы постите слишком быстро");
                break;
        }
    }

    @Override
    public void correctCaptcha() {
        Toast.makeText(getActivity(), "Сообщение отправлено!", Toast.LENGTH_SHORT).show();
//        SendMessageListener threadView = (SendMessageListener) getTargetFragment();
//        dismiss();
//        threadView.updateThread();
    }

    @Override
    public void showImage() {
        Glide.with(this)
                .load(Constants.LOAD_CAPTCHA_IMAGE + presenter.getCaptchaId())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(captchaImg);
    }

    private void provideEditText() {
        etCaptcha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 6) {
                    presenter.postMessage(s.toString());

                    View view = getActivity().getCurrentFocus();
                    InputMethodManager inputMethodManager = (InputMethodManager)
                            getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
    }

    private void showToastError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
