package com.caramelheaven.lennach.presentation.thread;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.presentation.base.BaseFragment;
import com.caramelheaven.lennach.presentation.comment_viewer.CommentViewerDialogFragment;
import com.caramelheaven.lennach.presentation.thread.presenter.ThreadPresenter;
import com.caramelheaven.lennach.presentation.thread.presenter.ThreadView;
import com.caramelheaven.lennach.utils.Constants;
import com.caramelheaven.lennach.utils.OnPostItemClickListener;
import com.caramelheaven.lennach.utils.bus.models.ActionThread;
import com.caramelheaven.lennach.utils.bus.GlobalBus;
import com.caramelheaven.lennach.utils.views.TopSheetBehavior;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class ThreadFragment extends BaseFragment implements ThreadView<Post> {

    private FrameLayout rootView;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TopSheetBehavior topSheetBehavior;
    private RelativeLayout rlContainerTopSheet;

    private ThreadAdapter adapter;

    @InjectPresenter
    ThreadPresenter presenter;

    @ProvidePresenter
    ThreadPresenter provideThreadPresenter() {
        return new ThreadPresenter(
                getArguments().getString("BOARD"),
                getArguments().getString("THREAD_NUM"));
    }

    public static ThreadFragment newInstance(String board, String threadNumber, int position) {

        Bundle args = new Bundle();
        args.putString("BOARD", board);
        args.putString("THREAD_NUM", threadNumber);
        args.putInt("POS", position);

        ThreadFragment fragment = new ThreadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thread, container, false);
        view.setTag(getArguments().getInt("POS"));

        view.setTranslationX(Constants.INSTANCE.getMANAGE_X_THREAD());

        return view;
    }

    private BroadcastReceiver broadcastReceiver;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topSheetBehavior.setState(TopSheetBehavior.STATE_COLLAPSED);
    }

    @Override
    public void onResume() {
        super.onResume();
        GlobalBus.getEventBus().register(this);
    }

    @Override
    public void onPause() {
        GlobalBus.getEventBus().unregister(this);
        super.onPause();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void openThread(ActionThread thread) {
        presenter.setBoardAndThread(thread.getBoard(), thread.getThreadNumber());
        presenter.getData();
    }

    @Override
    protected void provideRecyclerAndAdapter() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new ThreadAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.setOnPostItemClickListener(position -> {
            CommentViewerDialogFragment fragment = CommentViewerDialogFragment
                    .newInstance(new ArrayList<>(adapter.getItemByPosition(position).getRepliesPostList()));
            fragment.show(getActivity().getSupportFragmentManager(), null);
        });
    }

    @Override
    protected void provideClickListeners() {

    }

    @Override
    protected void initViews(View view) {
        rootView = view.findViewById(R.id.fragment_thread);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        rlContainerTopSheet = view.findViewById(R.id.rl_container_top_sheet);
        topSheetBehavior = TopSheetBehavior.from(rlContainerTopSheet);
    }

    @Override
    protected void deInitViews() {

    }

    @Override
    public void showProgress() {
        if (adapter.getItemCount() != 0) {
            adapter.clear();
        }
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showItems(List<Post> items) {
        if (items.size() != 0) {
            for (Post item : items) {
                item.setOnAnswerItemClickListener(reference -> {
                    CommentViewerDialogFragment fragment = CommentViewerDialogFragment.newInstance(reference);
                    fragment.show(getActivity().getSupportFragmentManager(), null);
                });
            }

            adapter.updateAdapter(items);
        }
    }
}
