package com.caramelheaven.lennach.presentation.board;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;
import com.caramelheaven.lennach.presentation.base.ParentFragment;
import com.caramelheaven.lennach.presentation.board.presenter.BoardPresenter;
import com.caramelheaven.lennach.presentation.board.presenter.BoardView;
import com.caramelheaven.lennach.utils.PaginationScrollListener;

import java.util.ArrayList;
import java.util.List;

public class BoardFragment extends ParentFragment implements BoardView<Usenet> {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private BoardAdapter boardAdapter;
    private LinearLayoutManager layoutManager;

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
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        initRecyclerAndAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView = null;
        progressBar = null;
    }

    @Override
    protected void initRecyclerAndAdapter() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,
                false);
        recyclerView.setLayoutManager(layoutManager);

        boardAdapter = new BoardAdapter(new ArrayList<>());
        recyclerView.setAdapter(boardAdapter);

        boardAdapter.setBoardOnItemClickListener(new BoardOnItemClickListener() {
            @Override
            public void onImageClick(int position, ImageView image) {
                Toast.makeText(getActivity(), "image", Toast.LENGTH_SHORT).show();

        /*        SliderImageDialogFragment dialogFragment = SliderImageDialogFragment.newInstance(position, boardAdapter.getUsenetList());
                dialogFragment.show(getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction(), null);*/
            }

            @Override
            public void onUsenetClick(int position) {
                Toast.makeText(getActivity(), "usenet", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                presenter.loadNextPage();
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
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showItems(List<Usenet> items) {
        boardAdapter.updateAdapter(items);
    }

    @Override
    public void showError() {

    }

    @Override
    public void refreshItems(List<Usenet> items) {

    }
}