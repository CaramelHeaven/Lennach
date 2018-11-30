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
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.board.Usenet;
import com.caramelheaven.lennach.presentation.base.BaseFragment;
import com.caramelheaven.lennach.presentation.board.presenter.BoardPresenter;
import com.caramelheaven.lennach.presentation.board.presenter.BoardView;
import com.caramelheaven.lennach.presentation.main.BoardAdapter;
import com.caramelheaven.lennach.utils.OnBoardItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class BoardFragment extends BaseFragment implements BoardView<Usenet> {

    private BoardAdapter boardAdapter;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @InjectPresenter
    BoardPresenter presenter;

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
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        provideRecyclerAndAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
    protected void provideRecyclerAndAdapter() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        boardAdapter = new BoardAdapter(new ArrayList<>());
        recyclerView.setAdapter(boardAdapter);

        boardAdapter.setOnBoardItemClickListener(new OnBoardItemClickListener() {
            @Override
            public void onImageClick(int pos, View view) {
                openViewImages(pos, view);
            }

            @Override
            public void onThreadClick(int pos) {

            }
        });
    }


    @Override
    public void showItems(List<Usenet> items) {
        if (items.size() != 0) {
            boardAdapter.updateAdapter(items);
            runLayoutAnimation();
        }
    }

    private void openViewImages(int pos, View view) {
        ImageView ivPhoto = (ImageView) view;

        //((TransitionSet) this.getExitTransition()).excludeTarget(ivPhoto, true);

//        this.getFragmentManager()
//                .beginTransaction()
//                .setReorderingAllowed(true)
//                .addSharedElement(ivPhoto, getResources().getString(R.string.image_transition))
//                .replace(R.id.fragment_container,
//                        ImageGalleryFragment.newInstance(boardAdapter.getItemByPosition(pos).getImage()),
//                        ImageGalleryFragment.class.getSimpleName())
//                .addToBackStack(null)
//                .commit();
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
