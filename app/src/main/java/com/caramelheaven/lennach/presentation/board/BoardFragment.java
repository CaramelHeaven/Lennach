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

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;
import com.caramelheaven.lennach.presentation.base.ParentFragment;
import com.caramelheaven.lennach.presentation.board.presenter.BoardPresenter;
import com.caramelheaven.lennach.presentation.board.presenter.BoardView;
import com.caramelheaven.lennach.presentation.image_viewer.ImageViewerFragment;
import com.caramelheaven.lennach.presentation.thread.ThreadFragment;
import com.caramelheaven.lennach.utils.Constants;
import com.caramelheaven.lennach.utils.callbacks.BottomBarHandler;
import com.caramelheaven.lennach.utils.PaginationScrollListener;
import com.caramelheaven.lennach.utils.channel.Channel;
import com.caramelheaven.lennach.utils.channel.SomeData;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class BoardFragment extends ParentFragment implements BoardView<Usenet> {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private BoardAdapter boardAdapter;
    private LinearLayoutManager layoutManager;
    private BottomBarHandler bottomBarHandler;


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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof BottomBarHandler) {
            bottomBarHandler = (BottomBarHandler) getActivity();
        }
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
        recyclerView.setNestedScrollingEnabled(false);

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,
                false);
        layoutManager.setInitialPrefetchItemCount(0);
        recyclerView.setLayoutManager(layoutManager);

        boardAdapter = new BoardAdapter(new ArrayList<>());
        recyclerView.setAdapter(boardAdapter);

        boardAdapter.setBoardOnItemClickListener(new BoardOnItemClickListener() {
            @Override
            public void onImageClick(int position, ImageView image) {
                startViewerImages(position, image);
            }

            @Override
            public void onUsenetClick(int position) {
                Channel.sendData(new SomeData(Constants.ENTER_FAB_STATE));
                Usenet usenet = boardAdapter.getItemByPosition(position);
                presenter.saveThreadInNavigation(usenet);
                String threadId = usenet.getNum();
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container,
                                ThreadFragment.newInstance(getArguments().getString("BOARD_NAME"), threadId))
                        .commit();
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
    public void onDestroy() {
        super.onDestroy();
        Timber.d("onDesctoyed");
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

    @Override
    public void showMainBottomBar(SomeData data) {
        bottomBarHandler.interactionBottomVisibility(data);
    }

    private void startViewerImages(int position, ImageView image) {
        ImageViewerFragment currentGallery = ImageViewerFragment.newInstance(position, boardAdapter.getUsenetList());
        bottomBarHandler.interactionBottomVisibility(new SomeData(Constants.HIDE_BOTTOM_BAR));

        getFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.fragment_container, currentGallery)
                .commit();
    }
}