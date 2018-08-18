package com.caramelheaven.lennach.ui.board;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.datasource.database.entity.helpers.PostFileThread;
import com.caramelheaven.lennach.datasource.database.entity.helpers.PostsInThreads;
import com.caramelheaven.lennach.ui.board.presenter.BoardPresenter;
import com.caramelheaven.lennach.ui.board.presenter.BoardView;
import com.caramelheaven.lennach.ui.thread.ThreadFragment;
import com.caramelheaven.lennach.utils.PaginationScrollListener;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 27.07.2018
 */
public class BoardFragment extends MvpAppCompatFragment implements BoardView<PostFileThread> {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private LinearLayoutManager layoutManager;
    private BoardAdapter adapter;

    @InjectPresenter
    BoardPresenter presenter;

    @ProvidePresenter
    BoardPresenter provideThreadPresenter() {
        return new BoardPresenter(getArguments().getString("BOARD_NAME"));
    }

    public static BoardFragment newInstance(String boardName) {
        Bundle args = new Bundle();
        args.putString("BOARD_NAME", boardName);

        BoardFragment fragment = new BoardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BoardAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        Timber.d("savedInstanceState: " + savedInstanceState);

        adapter.setMyOnItemClickListener(position -> {
            String idThread = adapter.getThreadIdByPosition(position);
            Timber.d("getPosition: " + idThread);
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, ThreadFragment
                            .newInstance(getArguments().getString("BOARD_NAME"), idThread))
                    .addToBackStack(null)
                    .commit();
        });

        recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                presenter.loadMoreThreads();
            }

            @Override
            protected boolean isLoading() {
                return presenter.isLoading();
            }

            @Override
            protected boolean isLastPage() {
                return presenter.isLastPage();
            }
        });
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
    public void showError() {

    }

    @Override
    public void refteshItems(List<PostFileThread> models) {

    }

    @Override
    public void showItems(List<PostFileThread> models) {
        Timber.d("models size: " + models.size());
        adapter.updateAdapter(models);
    }
}
