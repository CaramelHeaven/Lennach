package com.caramelheaven.lennach.presentation.main.saved_history;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.base.ParentFragment;
import com.caramelheaven.lennach.presentation.main.saved_history.presenter.HistoryPresenter;
import com.caramelheaven.lennach.presentation.main.saved_history.presenter.HistoryView;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends ParentFragment implements HistoryView {

    @InjectPresenter
    HistoryPresenter presenter;

    @ProvidePresenter
    HistoryPresenter provideHistoryPresenter() {
        return new HistoryPresenter();
    }

    private RecyclerView rvFavourite, rvRecently;

    private HistoryFavouriteAdapter adapterFavourite;
    private HistoryRecentlyAdapter adapterRecently;

    public static HistoryFragment newInstance() {

        Bundle args = new Bundle();

        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_navigation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavourite = view.findViewById(R.id.rv_favorite);
        rvRecently = view.findViewById(R.id.rv_recently);

        initRecyclerAndAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void updateRecentlyAdapter(List items) {

    }

    @Override
    public void updateFavouriteAdapter(List usenets) {
        if (usenets.size() != 0) {
            adapterFavourite.updateAdapter(usenets);
        }
    }

    @Override
    protected void initRecyclerAndAdapter() {
        rvFavourite.setHasFixedSize(true);
        rvRecently.setHasFixedSize(true);

        rvFavourite.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvRecently.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapterFavourite = new HistoryFavouriteAdapter(new ArrayList<>());
        adapterRecently = new HistoryRecentlyAdapter(new ArrayList<>());

        rvFavourite.setAdapter(adapterFavourite);
        rvRecently.setAdapter(adapterRecently);
    }
}
