package com.caramelheaven.lennach.presentation.get_captcha;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.caramelheaven.lennach.presentation.get_captcha.presenter.CaptchaView;

/**
 * Created by CaramelHeaven on 02:42, 04/01/2019.
 */
public class CaptchaDialogFragment extends MvpAppCompatDialogFragment implements CaptchaView {

    public static CaptchaDialogFragment newInstance() {

        Bundle args = new Bundle();

        CaptchaDialogFragment fragment = new CaptchaDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
