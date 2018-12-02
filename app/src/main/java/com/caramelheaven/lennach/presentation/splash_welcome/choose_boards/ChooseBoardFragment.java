package com.caramelheaven.lennach.presentation.splash_welcome.choose_boards;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caramelheaven.lennach.presentation.base.BaseFragment;

public class ChooseBoardFragment extends BaseFragment {

    public static ChooseBoardFragment newInstance() {

        Bundle args = new Bundle();

        ChooseBoardFragment fragment = new ChooseBoardFragment();
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
    protected void provideRecyclerAndAdapter() {

    }

    @Override
    protected void provideClickListeners() {

    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void deInitViews() {

    }
}
