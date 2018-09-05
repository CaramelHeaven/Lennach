package com.caramelheaven.lennach.ui.thread;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
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
import com.caramelheaven.lennach.datasource.database.entity.iFile;
import com.caramelheaven.lennach.ui.base.BaseFragment;
import com.caramelheaven.lennach.ui.slider.SliderImageDialogFragment;
import com.caramelheaven.lennach.ui.slider.presenter.SliderImageView;
import com.caramelheaven.lennach.ui.thread.helper.RecyclerTouchListener;
import com.caramelheaven.lennach.ui.thread.helper.ThreadClickListener;
import com.caramelheaven.lennach.ui.thread.presenter.ThreadPresenter;
import com.caramelheaven.lennach.ui.thread.presenter.ThreadView;
import com.caramelheaven.lennach.utils.imageOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ThreadFragment extends MvpAppCompatFragment implements ThreadView, BaseFragment {

    private RecyclerView rvContaner;
    private ProgressBar progressBar;

    private ThreadAdapter adapter;

    public static ThreadFragment newInstance(String boardName, String idThread) {
        Bundle args = new Bundle();
        args.putString("BOARD_NAME", boardName);
        args.putString("THREAD_ID", idThread);

        ThreadFragment fragment = new ThreadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @InjectPresenter
    ThreadPresenter presenter;

    @ProvidePresenter
    ThreadPresenter provideThreadPresenter() {
        return new ThreadPresenter(getArguments()
                .getString("BOARD_NAME"), getArguments().getString("THREAD_ID"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thread, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvContaner = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        provideRecyclerAndAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rvContaner = null;
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
        adapter.updateAdapter(posts);
    }

    @Override
    public void provideRecyclerAndAdapter() {
        rvContaner.setHasFixedSize(true);
        rvContaner.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new ThreadAdapter(new ArrayList<>(),getContext());
        rvContaner.setAdapter(adapter);

        adapter.setImageOnItemClickListener((view, position) -> {
            SliderImageDialogFragment dialogFragment = SliderImageDialogFragment.newInstance(position, mappingFiles(adapter.getItems()));
            dialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);
            dialogFragment.show(getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction(), null);
        });

        rvContaner.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvContaner, new ThreadClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private ArrayList<iFile> mappingFiles(List<PostsHelper> postsHelpers) {
        ArrayList<iFile> fileList = new ArrayList<>();
        for (PostsHelper post : postsHelpers) {
            if (post.iFileList.size() != 0) {
                fileList.addAll(post.iFileList);
            }
        }
        return fileList;
    }
}
