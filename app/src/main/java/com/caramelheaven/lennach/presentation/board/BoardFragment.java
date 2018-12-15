package com.caramelheaven.lennach.presentation.board;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.board.Usenet;
import com.caramelheaven.lennach.presentation.base.BaseFragment;
import com.caramelheaven.lennach.presentation.board.presenter.BoardPresenter;
import com.caramelheaven.lennach.presentation.board.presenter.BoardView;
import com.caramelheaven.lennach.utils.OnBoardItemClickListener;
import com.caramelheaven.lennach.utils.PaginationScrollListener;
import com.caramelheaven.lennach.utils.bus.GlobalBus;
import com.caramelheaven.lennach.utils.bus.models.Kek;

import java.util.ArrayList;
import java.util.List;

public class BoardFragment extends BaseFragment implements BoardView<Usenet> {

    private BoardAdapter adapter;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayoutManager linearLayoutManager;

    @InjectPresenter
    BoardPresenter presenter;

    @ProvidePresenter
    BoardPresenter provideBoardPresenter() {
        return new BoardPresenter(getArguments().getString("BOARD"));
    }

    public static BoardFragment newInstance(String board, int position) {

        Bundle args = new Bundle();
        args.putString("BOARD", board);
        args.putInt("POS", position);

        BoardFragment fragment = new BoardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        view.setTag(getArguments().getInt("POS", 0));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showItems(List<Usenet> items, boolean withAnimation) {
        adapter.updateAdapter(items);

        if (withAnimation) {
            runLayoutAnimation();
        } else {
            adapter.notifyDataSetChanged();
        }
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
    protected void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
    }

    @Override
    protected void deInitViews() {
        recyclerView = null;
        progressBar = null;
    }

    @Override
    protected void provideRecyclerAndAdapter() {
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new BoardAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
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
    protected void provideClickListeners() {
        adapter.setOnBoardItemClickListener(new OnBoardItemClickListener() {
            @Override
            public void onImageClick(int pos, View view) {
                int currentPos = adapter.getPositionByItem(adapter.getItemByPosition(pos));
                GlobalBus.getEventBus().post(new Kek("f", view, pos, currentPos, adapter.getImages()));
            }

            @Override
            public void onThreadClick(int pos) {
                GlobalBus.getEventBus().post(presenter.mappingUsenet(adapter.getItemByPosition(pos)));
            }
        });
    }

    private void runLayoutAnimation() {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_from_bottom);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}
