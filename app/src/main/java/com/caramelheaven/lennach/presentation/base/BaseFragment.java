package com.caramelheaven.lennach.presentation.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatFragment;

public abstract class BaseFragment extends MvpAppCompatFragment {

    protected abstract void provideRecyclerAndAdapter();

    protected abstract void provideClickListeners();

    protected abstract void initViews(View view);

    protected abstract void deInitViews();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);

        provideRecyclerAndAdapter();
        provideClickListeners();
    }

    @Override
    public void onDestroyView() {
        deInitViews();
        super.onDestroyView();
    }
}
