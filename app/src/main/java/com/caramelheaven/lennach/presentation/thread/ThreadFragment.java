package com.caramelheaven.lennach.presentation.thread;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.thread_viewer.Post;
import com.caramelheaven.lennach.presentation.base.ParentFragment;
import com.caramelheaven.lennach.presentation.captcha.CaptchaDialogFragment;
import com.caramelheaven.lennach.presentation.thread.presenter.ThreadPresenter;
import com.caramelheaven.lennach.presentation.thread.presenter.ThreadView;
import com.caramelheaven.lennach.utils.callbacks.BottomBarHandler;
import com.caramelheaven.lennach.utils.item_touch.ItemTouchHelperCallback;
import com.caramelheaven.lennach.utils.view.TopSheetBehavior;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class ThreadFragment extends ParentFragment implements ThreadView<Post> {

    private RecyclerView rvContainer;
    private ProgressBar progressBar;
    private TopSheetBehavior topSheetBehavior;
    private LinearLayout llContainer;
    private EditText etMessage;
    private CoordinatorLayout coordinatorLayout;
    private ImageButton btnSend, btnCatalog;
    private TextView tvCounter;

    private ThreadAdapter threadAdapter;
    private StringBuilder cacheAnswer;
    private BottomBarHandler bottomBarHandler;

    @InjectPresenter
    ThreadPresenter presenter;

    @ProvidePresenter
    ThreadPresenter provideThreadPresenter() {
        return new ThreadPresenter(getArguments().getString("BOARD_NAME"),
                getArguments().getString("THREAD_ID"));
    }

    public static ThreadFragment newInstance(String boardName, String threadId) {

        Bundle args = new Bundle();
        args.putString("BOARD_NAME", boardName);
        args.putString("THREAD_ID", threadId);

        ThreadFragment fragment = new ThreadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thread, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvContainer = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        llContainer = view.findViewById(R.id.ll_container);
        topSheetBehavior = TopSheetBehavior.from(llContainer);
        etMessage = llContainer.findViewById(R.id.et_message);
        coordinatorLayout = view.findViewById(R.id.coordinatorLayout);
        topSheetBehavior.setState(TopSheetBehavior.STATE_HIDDEN);
        btnSend = view.findViewById(R.id.btn_send);
        btnCatalog = view.findViewById(R.id.btn_catalog);
        tvCounter = view.findViewById(R.id.tv_counter);

        cacheAnswer = new StringBuilder();
        topSheetBehavior.setState(TopSheetBehavior.STATE_HIDDEN);

        initRecyclerAndAdapter();
        initScrollBehavior();
        initTopSheet();
        initEtMessageListeners();
        initButtons();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof BottomBarHandler) {
            bottomBarHandler = (BottomBarHandler) getActivity();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        progressBar = null;
        rvContainer = null;
        btnCatalog = null;
        btnSend = null;
        tvCounter = null;
        topSheetBehavior = null;
        etMessage = null;
        llContainer = null;
    }

    @Override
    protected void initRecyclerAndAdapter() {
        rvContainer.setHasFixedSize(true);
        rvContainer.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        threadAdapter = new ThreadAdapter(new ArrayList());
        rvContainer.setAdapter(threadAdapter);

        ItemTouchHelperCallback callback = new ItemTouchHelperCallback(threadAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rvContainer);

        threadAdapter.setItemTouchCallback(post -> {
            etMessage.requestFocus();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            topSheetBehavior.setState(TopSheetBehavior.STATE_EXPANDED);
            String answerToPost = ">>" + post.getNum() + "\n";
            if (cacheAnswer.length() != 0) {
                cacheAnswer.setLength(0);
                cacheAnswer.append(etMessage.getText().toString()).append("\n").append(answerToPost);
            } else {
                cacheAnswer.append(answerToPost);
            }
            etMessage.setText("");
            etMessage.setText(cacheAnswer.toString());
            etMessage.setSelection(cacheAnswer.toString().length());
        });
    }

    @Override
    public void showItems(List<Post> items) {
        etMessage.setText("");
        etMessage.clearFocus();
        topSheetBehavior.setState(TopSheetBehavior.STATE_HIDDEN);
        threadAdapter.updateAdapter(items);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }


    private void initButtons() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = etMessage.getText().toString();
                String board = getArguments().getString("BOARD_NAME");
                String threadId = getArguments().getString("THREAD_ID");

                InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                CaptchaDialogFragment captchaDialog =
                        CaptchaDialogFragment.newInstance(board, threadId, msg);
                captchaDialog.setTargetFragment(ThreadFragment.this, 1337);
                captchaDialog.show(getActivity().getSupportFragmentManager(), null);
//                CaptchaDialog captchaDialog = CaptchaDialog.newInstance();
//                captchaDialog.setArguments(args);
//                captchaDialog.setTargetFragment(ThreadFragment.this, 1337);
//                captchaDialog.show(getFragmentManager(), "dialog");
            }
        });

        btnCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "get Catalog", Toast.LENGTH_SHORT).show();
                //ImageViewerDialogFragment fragment = ImageViewerDialogFragment.newInstance();
                //fragment.show(getFragmentManager(), null);
            }
        });
    }

    private void initEtMessageListeners() {
        etMessage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Timber.d("hasFocus: " + hasFocus);
            }
        });

        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvCounter.setText(s.toString().length() + "/2000");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void initTopSheet() {
        topSheetBehavior.setTopSheetCallback(new TopSheetBehavior.TopSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset, @Nullable Boolean isOpening) {
            }
        });
    }

    private void initScrollBehavior() {
        rvContainer.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                InputMethodManager imm = (InputMethodManager)
                        getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etMessage.getWindowToken(), 0);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        if (topSheetBehavior.getState() != TopSheetBehavior.STATE_HIDDEN) {
                            topSheetBehavior.setState(TopSheetBehavior.STATE_COLLAPSED);
                        }
                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:
                        //      Timber.d("юзер отпустил пальчик и лист не скролица");
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        //   Timber.d("SCROLL_STATE_SETTING, eto konez lista");
                        break;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    bottomBarHandler.scrollBehavior("HIDE");
                } else {
                    bottomBarHandler.scrollBehavior("SHOW");
                }
            }
        });

        coordinatorLayout.getViewTreeObserver().addOnScrollChangedListener(() -> {
            Rect r = new Rect();
            //r will be populated with the coordinates of your view that area still visible.
            coordinatorLayout.getWindowVisibleDisplayFrame(r);
            int screenHeight = coordinatorLayout.getRootView().getHeight();
            int heightDiff = coordinatorLayout.getRootView().getHeight() - r.top;

            if (!(heightDiff > screenHeight * 0.15)) {
                topSheetBehavior.setState(TopSheetBehavior.STATE_COLLAPSED);
            }
        });
    }
}
