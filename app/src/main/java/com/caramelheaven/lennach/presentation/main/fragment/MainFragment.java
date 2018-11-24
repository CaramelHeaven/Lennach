package com.caramelheaven.lennach.presentation.main.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.board.BoardFragment;
import com.caramelheaven.lennach.presentation.main.fragment.presenter.MainFrgmPresenter;
import com.caramelheaven.lennach.presentation.main.fragment.presenter.MainFrgmView;
import com.caramelheaven.lennach.utils.bus.GlobalBus;
import com.caramelheaven.lennach.utils.view.BottomNavigationViewEx;

import org.greenrobot.eventbus.EventBus;

public class MainFragment extends MvpAppCompatFragment implements MainFrgmView {
    private BottomNavigationViewEx btnNavigation;

    @InjectPresenter
    MainFrgmPresenter presenter;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnNavigation = view.findViewById(R.id.bottom_navigation);

        provideBottomNavigation();

        provideItemStartPage();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void provideBottomNavigation() {
        btnNavigation.setTextVisibility(false);
        btnNavigation.enableItemShiftingMode(false);
        btnNavigation.enableAnimation(false);
        btnNavigation.enableShiftingMode(false);

        btnNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.item_navigation:
                    provideItemNavigation();
                    break;
                case R.id.item_start_page:
                    provideItemStartPage();
                    break;
                case R.id.item_notification:
                    provideItemNotification();
                    break;
                case R.id.item_menu:
                    provideItemMenu();
                    break;
            }
            return true;
        });
    }

    private void provideItemNavigation() {
        GlobalBus.getEventBus().post("event");
    }

    private void provideItemStartPage() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_main, BoardFragment.newInstance())
                .commit();
    }

    private void provideItemNotification() {

    }

    private void provideItemMenu() {

    }
}
