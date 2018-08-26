package com.caramelheaven.lennach.ui.welcome.container;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.ui.base.WelcomeFragment;
import com.caramelheaven.lennach.ui.welcome.container.presenter.FragmentSecondPresenter;
import com.caramelheaven.lennach.ui.welcome.container.view.SecondView;
import com.caramelheaven.lennach.utils.view.WelcomeButton;

import java.util.List;

public class FragmentSecond extends WelcomeFragment implements SecondView {

    @InjectPresenter
    FragmentSecondPresenter presenter;

    public static FragmentSecond newInstance() {

        Bundle args = new Bundle();

        FragmentSecond fragment = new FragmentSecond();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome_second, container, false);
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
    protected void provideTouchEvents() {

    }

    @Override
    public void showItems(List<WelcomeButton> models) {

    }
}
