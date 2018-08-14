package com.caramelheaven.lennach.ui.main.navigation.add;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.datasource.model.BoardNavModel;
import com.caramelheaven.lennach.ui.base.BaseFragment;
import com.caramelheaven.lennach.ui.main.navigation.add.presenter.AddDialogPresenter;
import com.caramelheaven.lennach.ui.main.navigation.add.presenter.AddDialogView;
import com.caramelheaven.lennach.utils.myOnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import timber.log.Timber;

public class AddDialogFragment extends MvpAppCompatDialogFragment implements BaseFragment, AddDialogView {

    private RecyclerView rvBoards;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private ImageView ivArrowCheck;

    private AddDialogAdapter adapter;
    private DisplayMetrics displayMetrics;
    private AnimatedVectorDrawable avdArrowToCheck;
    private AnimatedVectorDrawable avdCheckToArrow;

    @InjectPresenter
    AddDialogPresenter presenter;

    public static AddDialogFragment newInstance() {

        Bundle args = new Bundle();

        AddDialogFragment fragment = new AddDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);
        return inflater.inflate(R.layout.fragment_add_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvBoards = view.findViewById(R.id.rv_boards);
        progressBar = view.findViewById(R.id.progressBar);
        toolbar = view.findViewById(R.id.toolbar);
        ivArrowCheck = view.findViewById(R.id.iv_arrow_and_check);

        avdArrowToCheck = (AnimatedVectorDrawable) getActivity().getDrawable(R.drawable.avd_arrow_to_check);
        avdCheckToArrow = (AnimatedVectorDrawable) getActivity().getDrawable(R.drawable.avd_check_to_arrow);
        ivArrowCheck.setImageDrawable(avdArrowToCheck);

        displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        provideRecyclerAndAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rvBoards = null;
        progressBar = null;
        Timber.d("onDestroyView");
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(displayMetrics.widthPixels, displayMetrics.heightPixels / 2);
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.saveSelectedPos(adapter.getSelectedPositions());
    }

    @Override
    public void provideRecyclerAndAdapter() {
        rvBoards.setHasFixedSize(true);
        rvBoards.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new AddDialogAdapter(new ArrayList<>());
        rvBoards.setAdapter(adapter);
        adapter.setMySpesialOnItemClickListener((position, selected) -> {
            if (selected) {
                ivArrowCheck.setImageDrawable(avdArrowToCheck);
                provideArrowCheckAnimation();
                //bad
                provideImageClick();
            }
        });
    }

    @Override
    public void showItems(List<BoardNavModel> models) {
        Timber.d("models> " + models.size());
        presenter.getSaveSelectedPos();
        adapter.updateAdapter(models);
    }

    @Override
    public void showSelectedItems(Set<Integer> models) {
        adapter.setSelectedItems(models);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    public void sendBackResult() {
        AddDialogListener listener = (AddDialogListener) getTargetFragment();
        listener.onFinish(adapter.getSelectedItems());
        dismiss();
    }

    public interface AddDialogListener<T> {
        void onFinish(List<T> models);
    }

    private void provideArrowCheckAnimation() {
        AnimatedVectorDrawable temp = (AnimatedVectorDrawable) ivArrowCheck.getDrawable();
        Drawable drawable = temp.getCurrent();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    private void provideImageClick() {
        ivArrowCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendBackResult();
            }
        });
    }
}