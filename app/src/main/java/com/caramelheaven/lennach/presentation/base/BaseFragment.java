package com.caramelheaven.lennach.presentation.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.caramelheaven.lennach.utils.bus.GlobalBus;

public abstract class BaseFragment extends MvpAppCompatFragment {

    protected abstract void provideRecyclerAndAdapter();

    protected abstract void provideClickListeners();

    protected abstract void initViews(View view);

    protected abstract void deInitViews();

    protected abstract Boolean enableEventBus();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);

        provideRecyclerAndAdapter();
        provideClickListeners();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (enableEventBus()) {
            GlobalBus.getEventBus().register(this);
        }
    }

    @Override
    public void onPause() {
        if (enableEventBus()) {
            GlobalBus.getEventBus().unregister(this);
        }
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        deInitViews();
        super.onDestroyView();
    }

    /* Show keyboard for input text
     * */
    protected void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}
