package com.caramelheaven.lennach.presentation.thread;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.caramelheaven.lennach.utils.bus.GlobalBus;
import com.caramelheaven.lennach.utils.bus.models.ActionThread;
import com.caramelheaven.lennach.utils.views.TopSheetBehavior;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class ThreadFragment extends BaseFragment implements ThreadView<Post> {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TopSheetBehavior topSheetBehavior;
    private RelativeLayout rlContainerTopSheet;
    private ImageButton btnSend, btnMore, btnCatalog;
    private EditText etMessage, etName, etOptions;

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

    boolean kek = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button btn = view.findViewById(R.id.button1);
        topSheetBehavior.setState(TopSheetBehavior.STATE_COLLAPSED);

        btnMore.setOnClickListener(v -> {
            Timber.d("kek");
            if (etOptions.getVisibility() == View.VISIBLE || etName.getVisibility() == View.VISIBLE) {
                etOptions.setVisibility(View.GONE);
                etName.setVisibility(View.GONE);

                //testing this later
                topSheetBehavior.setPeekHeight(dpToPx(180, getActivity()));

                topSheetBehavior.setState(TopSheetBehavior.STATE_COLLAPSED);
            } else {
                etName.setVisibility(View.VISIBLE);
                etOptions.setVisibility(View.VISIBLE);
                topSheetBehavior.setState(TopSheetBehavior.STATE_EXPANDED);
            }
        });


        topSheetBehavior.setTopSheetCallback(new TopSheetBehavior.TopSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == TopSheetBehavior.STATE_COLLAPSED) {

                } else if (newState == TopSheetBehavior.STATE_EXPANDED) {

                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset, @Nullable Boolean isOpening) {

            }
        });
    }

    float lastMove = 0;

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
    }

    @Override
    protected void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        rlContainerTopSheet = view.findViewById(R.id.cl_container_top_sheet);
        topSheetBehavior = TopSheetBehavior.from(rlContainerTopSheet);
        btnSend = rlContainerTopSheet.findViewById(R.id.btn_send);
        btnCatalog = rlContainerTopSheet.findViewById(R.id.btn_catalog);
        btnMore = rlContainerTopSheet.findViewById(R.id.btn_more);
        etMessage = rlContainerTopSheet.findViewById(R.id.et_message);
        etName = rlContainerTopSheet.findViewById(R.id.et_name);
        etOptions = rlContainerTopSheet.findViewById(R.id.et_options);
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

    public int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        int kek = Math.round((float) dp * density);
        Timber.d("kek: " + kek);

        return kek;
    }
}
