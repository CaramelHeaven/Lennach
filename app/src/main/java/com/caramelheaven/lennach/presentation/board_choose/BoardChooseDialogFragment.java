package com.caramelheaven.lennach.presentation.board_choose;

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

/**
 * Created by CaramelHeaven on 19:24, 13/01/2019.
 */
public class BoardChooseDialogFragment extends MvpAppCompatDialogFragment implements BoardChooseView<BoardAll> {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Button btnAdd, btnSelectAll;

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

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new BoardChooseAdapter(getActivity().getLayoutInflater(), new ArrayList());
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void updateValues(List<BoardAll> values) {
        if (values.size() > 0) {
            adapter.setData(values);
        }
    }
}
