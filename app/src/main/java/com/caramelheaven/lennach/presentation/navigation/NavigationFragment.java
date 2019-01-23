package com.caramelheaven.lennach.presentation.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.common.Delegatable;
import com.caramelheaven.lennach.presentation.base.BaseFragment;
import com.caramelheaven.lennach.presentation.board_choose.BoardChooseDialogFragment;
import com.caramelheaven.lennach.presentation.navigation.callbacks.OnSavedCloseDialogFragment;
import com.caramelheaven.lennach.presentation.navigation.presenter.NavigationPresenter;
import com.caramelheaven.lennach.presentation.navigation.presenter.NavigationView;
import com.caramelheaven.lennach.utils.Constants;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class NavigationFragment extends BaseFragment implements NavigationView, OnSavedCloseDialogFragment {

    private RecyclerView recyclerView;

    private NavigationAdapter adapter;

    @InjectPresenter
    NavigationPresenter presenter;

    public static NavigationFragment newInstance() {

        Bundle args = new Bundle();

        NavigationFragment fragment = new NavigationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }

    @Override
    protected void provideRecyclerAndAdapter() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new NavigationAdapter(getActivity().getLayoutInflater(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void provideClickListeners() {

    }

    @Override
    protected void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    @Override
    protected void deInitViews() {
        recyclerView = null;
    }

    @Override
    protected Boolean enableEventBus() {
        return true;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * When user click on add button, show dialog fragment which contains of boards from server
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addNewBoard(String action) {
        if (action.equals(Constants.INSTANCE.getADD_NEW_BOARD())) {
            BoardChooseDialogFragment fragment = BoardChooseDialogFragment.newInstance();
            fragment.setOnCloseDialogFragment(this::closeDialog);
            //fragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFragmentTheme);

            fragment.show(getActivity().getSupportFragmentManager(), null);
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showFavouriteData(List<Delegatable> items) {
        Timber.d("showFavouriteData");
        adapter.setData(items);
    }

    @Override
    public void closeDialog() {
        Timber.d("CLOSED");
    }
}
