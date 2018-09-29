package com.caramelheaven.lennach.presentation.thread;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.thread_viewer.Post;
import com.caramelheaven.lennach.presentation.base.ParentFragment;
import com.caramelheaven.lennach.presentation.thread.presenter.ThreadPresenter;
import com.caramelheaven.lennach.presentation.thread.presenter.ThreadView;

import java.util.ArrayList;
import java.util.List;

public class ThreadFragment extends ParentFragment implements ThreadView<Post> {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private ThreadAdapter threadAdapter;

    @InjectPresenter
    ThreadPresenter presenter;

    @ProvidePresenter
    ThreadPresenter provideThreadPresenter() {
        return new ThreadPresenter(getArguments().getString("BOARD_NAME"),
                getArguments().getString("THREAD_ID"));
    }

    public static ThreadFragment newInstance(String boardName, String threadId) {

        Bundle args = new Bundle();
        args.putString("BOARD_NAME", boardName);
        args.putString("THREAD_ID", threadId);

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
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        initRecyclerAndAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void initRecyclerAndAdapter() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        threadAdapter = new ThreadAdapter(new ArrayList());
        recyclerView.setAdapter(threadAdapter);
    }

    @Override
    public void showItems(List<Post> items) {
        threadAdapter.updateAdapter(items);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
