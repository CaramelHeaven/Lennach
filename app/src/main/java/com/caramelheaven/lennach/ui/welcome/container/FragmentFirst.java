package com.caramelheaven.lennach.ui.welcome.container;

import android.content.Intent;
import android.graphics.Color;
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
import com.caramelheaven.lennach.ui.main.MainActivity;
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

        Button btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class).putExtra("ITEM", "b"));
                getActivity().finish();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.savedItems(returnClickedItems());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        llContainer = null;
        btnStarted = null;
    }

    protected void provideTouchEvents() {


        btnStarted.setOnClickListener(view -> {
            WelcomeButton bu = (WelcomeButton) wbList.get(0).getChildAt(0);
            Toast.makeText(getActivity(), "all: " + wbList.size() + " count: ", Toast.LENGTH_SHORT).show();
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
