package com.caramelheaven.lennach.presentation.thread;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.base.fragment.BaseFragment;
import com.caramelheaven.lennach.utils.Constants;

/**
 * Created by CaramelHeaven on 18:07, 03/02/2019.
 */
public class ThreadFragment extends BaseFragment {
    public static ThreadFragment newInstance() {

        Bundle args = new Bundle();

        ThreadFragment fragment = new ThreadFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thread, container, false);
        //init slider in view pager
        view.setTag(getArguments().getInt("POS"));
        view.setTranslationX(Constants.INSTANCE.getManageXPosition());

        return view;
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
    protected void initViews(View view) {

    }

    @Override
    protected void deInitViews() {

    }
}
