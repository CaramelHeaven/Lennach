package com.caramelheaven.lennach.presentation.comment_viewer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.presentation.comment_viewer.presenter.CommentViewerPresenter;
import com.caramelheaven.lennach.presentation.comment_viewer.presenter.CommentViewerView;
import com.caramelheaven.lennach.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 16:52, 15/12/2018.
 */
public class CommentViewerDialogFragment extends MvpAppCompatDialogFragment implements CommentViewerView<Post> {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RelativeLayout rlContainer;
    //  private Button btnClose, btnBack;

    private CommentAdapter adapter;
    private DisplayMetrics displayMetrics;

    @InjectPresenter
    CommentViewerPresenter presenter;

    @ProvidePresenter
    CommentViewerPresenter provideCommentViewerPresenter() {
        return new CommentViewerPresenter(getArguments().getString(Constants.INSTANCE.getREFERENCE_ITEM()));
    }

    public static CommentViewerDialogFragment newInstance(String reference) {

        Bundle args = new Bundle();
        args.putString(Constants.INSTANCE.getREFERENCE_ITEM(), reference);

        CommentViewerDialogFragment fragment = new CommentViewerDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_comment_viewer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        rlContainer = view.findViewById(R.id.rl_container);
        //  btnClose = view.findViewById(R.id.btn_close);
        //  btnBack = view.findViewById(R.id.btn_back);

        displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        initRecyclerAndAdapter();
        //  initClickListeners();
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        window.setLayout(displayMetrics.widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onShowComments(List<Post> posts) {
        if (posts.size() > 0) {
            adapter.updateAdapter(posts);

            initButtons();
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

    private void initRecyclerAndAdapter() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new CommentAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }

    private void initButtons() {
        Button btnClose = new Button(getActivity());
        Button btnBack = new Button(getActivity());

        btnBack.setText("close");
        rlContainer.addView(btnBack);
    }

//    private void initClickListeners() {
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
//
//        btnClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//    }
}
