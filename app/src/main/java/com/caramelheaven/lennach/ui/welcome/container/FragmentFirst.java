package com.caramelheaven.lennach.ui.welcome.container;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.ui.base.WelcomeFragment;
import com.caramelheaven.lennach.ui.welcome.container.presenter.FragmentFirstPresenter;
import com.caramelheaven.lennach.ui.welcome.container.view.FirstView;
import com.caramelheaven.lennach.utils.view.WelcomeButton;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class FragmentFirst extends MvpAppCompatFragment implements FirstView {

    private LinearLayout llContainer;
    private Button btnStarted;

    private List<RelativeLayout> wbList = new ArrayList<>();

    @InjectPresenter
    FragmentFirstPresenter presenter;

    public static FragmentFirst newInstance() {

        Bundle args = new Bundle();

        FragmentFirst fragment = new FragmentFirst();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        llContainer = view.findViewById(R.id.ll_container);
        btnStarted = view.findViewById(R.id.btn_get_started);

        provideTouchEvents();
        presenter.showSavedItems();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        llContainer = null;
        btnStarted = null;
        presenter.savedItems(returnClickedItems());
    }

    protected void provideTouchEvents() {
        for (int i = 0; i < llContainer.getChildCount(); i++) {
            LinearLayout llChild = (LinearLayout) llContainer.getChildAt(i);
            for (int item = 0; item < llChild.getChildCount(); item++) {
                RelativeLayout rl = (RelativeLayout) llChild.getChildAt(item);
                WelcomeButton wbClick = (WelcomeButton) rl.getChildAt(0);
                wbClick.setOnClickListener(view ->
                        wbClick.setClicked(rl.getChildAt(1)));
                wbList.add(rl);
            }
        }

        btnStarted.setOnClickListener(view -> {
            int count = 0;

            Toast.makeText(getActivity(), "all: " + wbList.size() + " count: " + count, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void showItems(List<RelativeLayout> models) {
        Timber.d("models: " + models);
        Timber.d("models: " + models);
        Timber.d("models: " + models);
        for (RelativeLayout rl : models) {
            WelcomeButton wb = (WelcomeButton) rl.getChildAt(0);
            wb.setRecovery(rl.getChildAt(1));
        }
    }

    private List<RelativeLayout> returnClickedItems() {
        List<RelativeLayout> temp = new ArrayList<>();
        for (RelativeLayout wb : wbList) {
            WelcomeButton welcomeButton = (WelcomeButton) wb.getChildAt(0);
            if (welcomeButton.isClicked()) {
                temp.add(wb);
            }
        }
        return temp;
    }
}
