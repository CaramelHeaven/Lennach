package com.caramelheaven.lennach.presentation.board;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.ChangeBounds;
import android.support.transition.Fade;
import android.support.transition.Slide;
import android.support.transition.TransitionInflater;
import android.support.transition.TransitionSet;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.caramelheaven.lennach.presentation.image_viewer.ImageViewerDialogFragment;
import com.caramelheaven.lennach.presentation.image_viewer.TestFragment;
import com.caramelheaven.lennach.presentation.thread.ThreadFragment;
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
                Fragment previosFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
//                ImageViewerDialogFragment nextFragment = ImageViewerDialogFragment.newInstance(boardAdapter.getUsenetList());
//                nextFragment.show(getActivity().getSupportFragmentManager(), null);

                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, TestFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                Fade exitFade = new Fade();
//                exitFade.setDuration(300);
//                previosFragment.setExitTransition(exitFade);
//
//                // 2. Shared Elements Transition
//                TransitionSet enterTransitionSet = new TransitionSet();
//                enterTransitionSet.addTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.move));
//                enterTransitionSet.setDuration(300);
//                enterTransitionSet.setStartDelay(300);
//                nextFragment.setSharedElementEnterTransition(enterTransitionSet);
//
//                // 3. Enter Transition for New Fragment
//                Fade enterFade = new Fade();
//                enterFade.setStartDelay(300 + 300);
//                enterFade.setDuration(300);
//                nextFragment.setEnterTransition(enterFade);
//                transaction.addSharedElement(image, image.getTransitionName());
//               // transaction.add(R.id.fragment_container, nextFragment);
//             //   transaction.commitAllowingStateLoss();
//                //transaction.commitAllowingStateLoss();
//               nextFragment.show(transaction, null);

                // nextFragment.show(getActivity().getSupportFragmentManager(), null);
            }

            @Override
            public void onUsenetClick(int position) {
                Toast.makeText(getActivity(), "usenet", Toast.LENGTH_SHORT).show();
                String threadId = boardAdapter.getItemByPosition(position).getNum();
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                ThreadFragment.newInstance(getArguments().getString("BOARD_NAME"), threadId))
                        .addToBackStack(null)
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