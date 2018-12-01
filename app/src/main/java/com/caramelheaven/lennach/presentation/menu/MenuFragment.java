package com.caramelheaven.lennach.presentation.menu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.base.BaseFragment;
import com.caramelheaven.lennach.presentation.menu.presenter.MenuPresenter;
import com.caramelheaven.lennach.presentation.menu.presenter.MenuView;

public class MenuFragment extends BaseFragment implements MenuView {

    @InjectPresenter
    MenuPresenter presenter;

    public static MenuFragment newInstance() {

        Bundle args = new Bundle();

        MenuFragment fragment = new MenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
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

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
