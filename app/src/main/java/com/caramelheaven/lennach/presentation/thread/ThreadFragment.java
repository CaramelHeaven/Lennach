package com.caramelheaven.lennach.presentation.thread;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.presentation.base.BaseFragment;
import com.caramelheaven.lennach.presentation.thread.presenter.ThreadPresenter;
import com.caramelheaven.lennach.presentation.thread.presenter.ThreadView;

import java.util.ArrayList;
import java.util.List;

public class ThreadFragment extends BaseFragment implements ThreadView<Post> {

    private FrameLayout rootView;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private ThreadAdapter adapter;

    @InjectPresenter
    ThreadPresenter presenter;

    @ProvidePresenter
    ThreadPresenter provideThreadPresenter() {
        return new ThreadPresenter(
                getArguments().getString("BOARD"),
                getArguments().getParcelable("FIRST_POST"));
    }

    public static ThreadFragment newInstance() {

        Bundle args = new Bundle();

        ThreadFragment fragment = new ThreadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thread, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void provideRecyclerAndAdapter() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new ThreadAdapter(presenter.getMainPost(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void provideClickListeners() {

    }

    @Override
    protected void initViews(View view) {
        rootView = view.findViewById(R.id.fragment_thread);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
    }

    @Override
    protected void deInitViews() {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showItems(List<Post> items) {
        if (items.size() != 0) {
            adapter.updateAdapter(items);
        }
    }
}
