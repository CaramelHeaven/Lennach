package com.caramelheaven.lennach.presentation.thread;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.presentation.base.BaseFragment;
import com.caramelheaven.lennach.presentation.comment_viewer.CommentViewerDialogFragment;
import com.caramelheaven.lennach.presentation.thread.presenter.ThreadPresenter;
import com.caramelheaven.lennach.presentation.thread.presenter.ThreadView;
import com.caramelheaven.lennach.utils.Constants;
import com.caramelheaven.lennach.utils.UtilsView;
import com.caramelheaven.lennach.utils.bus.GlobalBus;
import com.caramelheaven.lennach.utils.bus.models.ActionThread;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ThreadFragment extends BaseFragment implements ThreadView<Post> {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ImageButton btnSend, btnCatalogImages, btnMore;
    private EditText etMessage;
    private FrameLayout fmContainerMessanger;

    private ThreadAdapter adapter;
    private DisplayMetrics displayMetrics;

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

    int kek = 0;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        Button btn = view.findViewById(R.id.btn_test);

        UtilsView utilsView = UtilsView.getInstance();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kek == 0) {
                    kek = 1;
                    utilsView.expand(fmContainerMessanger, 300, utilsView.dpToPx(140, getActivity()));
                } else if (kek == 1) {
                    kek = 2;

                } else if (kek == 2) {
                    kek = 0;
                    //UtilsView.getInstance().collapse(fm, 300, 0);
                }
            }
        });
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
    }

    @Override
    protected void provideClickListeners() {
        adapter.setOnPostItemClickListener(position -> {
            CommentViewerDialogFragment fragment = CommentViewerDialogFragment
                    .newInstance(new ArrayList<>(adapter.getItemByPosition(position).getRepliesPostList()));
            fragment.show(getActivity().getSupportFragmentManager(), null);
        });

        btnMore.setOnClickListener(v -> {
            btnMore.animate().cancel();

            if (!presenter.isMoreOpened()) {
                btnMore.animate()
                        .rotation(180)
                        .start();

                presenter.setMoreOpened(true);
                UtilsView.getInstance().expand(fmContainerMessanger, 300,
                        UtilsView.getInstance().dpToPx(displayMetrics.heightPixels, getActivity()));
            } else {
                btnMore.animate()
                        .rotation(0)
                        .start();

                presenter.setMoreOpened(false);
                UtilsView.getInstance().collapse(fmContainerMessanger, 300,
                        UtilsView.getInstance().dpToPx(130, getActivity()));
            }
        });
    }

    @Override
    protected void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        btnSend = view.findViewById(R.id.btn_send);
        btnCatalogImages = view.findViewById(R.id.btn_catalog);
        btnMore = view.findViewById(R.id.btn_more);
        etMessage = view.findViewById(R.id.et_message);
        fmContainerMessanger = view.findViewById(R.id.fm_container_messanger);
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
