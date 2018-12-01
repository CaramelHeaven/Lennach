package com.caramelheaven.lennach.presentation.board;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.ChangeBounds;
import android.support.transition.Slide;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.caramelheaven.lennach.presentation.thread.ThreadFragment;
import com.caramelheaven.lennach.utils.OnBoardItemClickListener;
import com.caramelheaven.lennach.utils.bus.GlobalBus;
import com.caramelheaven.lennach.utils.bus.Kek;

import java.util.ArrayList;
import java.util.List;

public class BoardFragment extends BaseFragment implements BoardView<Usenet> {

    private BoardAdapter adapter;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @InjectPresenter
    BoardPresenter presenter;

    @ProvidePresenter
    BoardPresenter provideBoardPresenter() {
        return new BoardPresenter();
    }

    public static BoardFragment newInstance() {

        Bundle args = new Bundle();

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
    }

    @Override
    public void showItems(List<Usenet> items) {
        adapter.updateAdapter(items);
        runLayoutAnimation();
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


        adapter = new BoardAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
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
//                Intent intent = new Intent(getActivity(), ThreadActivity.class);
//                intent.putExtra("THREAD", adapter.getItemByPosition(pos).getThreadNum());
//
//                startActivity(intent);

                ThreadFragment fragment = ThreadFragment.newInstance();

                Slide slideTransition = new Slide(Gravity.RIGHT);
                slideTransition.setDuration(200);

                ChangeBounds changeBoundsTransition = new ChangeBounds();
                changeBoundsTransition.setDuration(200);

                fragment.setEnterTransition(slideTransition);
                fragment.setAllowReturnTransitionOverlap(true);
                fragment.setAllowEnterTransitionOverlap(true);
                fragment.setSharedElementEnterTransition(changeBoundsTransition);

                getFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
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
