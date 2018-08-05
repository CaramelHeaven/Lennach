package com.caramelheaven.lennach.ui.thread;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.datasource.database.entity.helpers.PostsHelper;
import com.caramelheaven.lennach.ui.thread.presenter.ThreadPresenter;
import com.caramelheaven.lennach.ui.thread.presenter.ThreadView;

import java.util.List;

import timber.log.Timber;

public class ThreadFragment extends MvpAppCompatFragment implements ThreadView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public static ThreadFragment newInstance(String idThread) {
        Bundle args = new Bundle();
        args.putString("ID", idThread);

        ThreadFragment fragment = new ThreadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @InjectPresenter
    ThreadPresenter presenter;

    @ProvidePresenter
    ThreadPresenter provideThreadPresenter() {
        return new ThreadPresenter("b", getArguments().getString("ID"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thread, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        Toast.makeText(getActivity(), "num: " + getArguments().getString("ID"), Toast.LENGTH_SHORT).show();
        presenter.getPosts(getArguments().getString("ID"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView = null;
        progressBar = null;
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
    public void hideRefreshing() {

    }

    @Override
    public void showRetryView(String cause) {

    }

    @Override
    public void hideRetryView() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void refteshItems(List<PostsHelper> posts) {

    }

    @Override
    public void showItems(List<PostsHelper> posts) {
        Timber.d("CHECKING SIZE: " + posts.size());
        for (PostsHelper helper : posts) {
            Timber.d("posts kek: " + helper.iPost);
        }
    }
}
