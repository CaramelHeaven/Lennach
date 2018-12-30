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
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.presentation.comment_viewer.presenter.CommentViewerPresenter;
import com.caramelheaven.lennach.presentation.comment_viewer.presenter.CommentViewerView;
import com.caramelheaven.lennach.utils.Constants;
import com.caramelheaven.lennach.utils.OnPostItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 16:52, 15/12/2018.
 */
public class CommentViewerDialogFragment extends MvpAppCompatDialogFragment implements CommentViewerView<Post> {

    private RecyclerView recyclerView;
    private Button btnClose, btnBack;
    private LinearLayout llContainer;

    private CommentAdapter adapter;
    private DisplayMetrics displayMetrics;

    @InjectPresenter
    CommentViewerPresenter presenter;

    @ProvidePresenter
    CommentViewerPresenter provideCommentViewerPresenter() {
        if (getArguments() != null) {
            if (getArguments().getString(Constants.INSTANCE.getSTRING()) != null) {
                return new CommentViewerPresenter(getArguments()
                        .getString(Constants.INSTANCE.getSTRING()), new ArrayList<>());
            } else if (getArguments().getParcelableArrayList(Constants.INSTANCE.getARRAY()) != null) {
                return new CommentViewerPresenter("", getArguments()
                        .getStringArrayList(Constants.INSTANCE.getARRAY()));
            }
        }
        return null;
    }

    public static CommentViewerDialogFragment newInstance(String reference) {

        Bundle args = new Bundle();
        args.putString(Constants.INSTANCE.getSTRING(), reference);

        CommentViewerDialogFragment fragment = new CommentViewerDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static CommentViewerDialogFragment newInstance(ArrayList<String> references) {

        Bundle args = new Bundle();
        args.putStringArrayList(Constants.INSTANCE.getARRAY(), references);

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
        llContainer = view.findViewById(R.id.ll_container);
        btnBack = view.findViewById(R.id.btn_back);
        btnClose = view.findViewById(R.id.btn_close);

        displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        initRecyclerAndAdapter();
        initClickListeners();
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

            llContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showProgress() {
        //progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        //progressBar.setVisibility(View.GONE);
    }

    private void initRecyclerAndAdapter() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new CommentAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.setOnPostItemClickListener(new OnPostItemClickListener() {
            @Override
            public void onBtnReplyClick(int position) {
                CommentViewerDialogFragment fragment = CommentViewerDialogFragment
                        .newInstance(new ArrayList<>(adapter.getItemByPosition(position).getRepliesPostList()));
                fragment.show(getActivity().getSupportFragmentManager(), null);
            }
        });
    }

    private void initClickListeners() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dismiss();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
