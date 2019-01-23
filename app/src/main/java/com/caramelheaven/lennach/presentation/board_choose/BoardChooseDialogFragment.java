package com.caramelheaven.lennach.presentation.board_choose;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.board.BoardAll;
import com.caramelheaven.lennach.presentation.board_choose.adapter.BoardChooseAdapter;
import com.caramelheaven.lennach.presentation.board_choose.presenter.BoardChoosePresenter;
import com.caramelheaven.lennach.presentation.board_choose.presenter.BoardChooseView;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 19:24, 13/01/2019.
 */
public class BoardChooseDialogFragment extends MvpAppCompatDialogFragment implements BoardChooseView<BoardAll> {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Button btnAdd, btnSelectAll, btnCancel;
    private EditText etSearch;

    private DisplayMetrics displayMetrics;
    protected BoardChooseAdapter adapter;

    @InjectPresenter
    BoardChoosePresenter presenter;

    public static BoardChooseDialogFragment newInstance() {

        Bundle args = new Bundle();

        BoardChooseDialogFragment fragment = new BoardChooseDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_board_choose, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        btnAdd = view.findViewById(R.id.btn_add);
        btnSelectAll = view.findViewById(R.id.btn_select_all);
        etSearch = view.findViewById(R.id.et_search);
        btnCancel = view.findViewById(R.id.btn_cancel);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new BoardChooseAdapter(getActivity().getLayoutInflater(), new ArrayList());
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            Timber.d("lek");
            Timber.d("ada: " + adapter.filterItems());
        });

        btnSelectAll.setOnClickListener(v -> {
            if (presenter.isAllSelected()) {
                for (BoardAll board : adapter.getItems()) {
                    board.setSelected(false);
                }
                presenter.setAllSelected(false);
            } else {
                for (BoardAll board : adapter.getItems()) {
                    board.setSelected(true);
                }
                presenter.setAllSelected(true);
            }

            adapter.notifyDataSetChanged();

        });

        btnCancel.setOnClickListener(v -> dismiss());

        displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        provideSearch();
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        window.setLayout(displayMetrics.widthPixels - 50, displayMetrics.heightPixels - 100);
        window.setGravity(Gravity.CENTER);
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
    public void updateValues(List<BoardAll> values) {
        if (values.size() > 0) {
            adapter.setData(values);
        }
    }

    private void provideSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                List<BoardAll> fitlerList = new ArrayList<>();
                String text = s.toString().toLowerCase();

                for (BoardAll boardAll : presenter.getSearchList()) {
                    if (boardAll.getName().toLowerCase().contains(text) ||
                            boardAll.getCategory().toLowerCase().contains(text) ||
                            boardAll.getId().toLowerCase().contains(text)) {
                        fitlerList.add(boardAll);
                    }
                }

                adapter.setData(fitlerList);
            }
        });
    }
}
