package com.caramelheaven.lennach.controllers.start;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caramelheaven.lennach.MyApplication;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.databinding.ItemAddBoardBinding;
import com.caramelheaven.lennach.datasourse.model.Board;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddBoardFragment extends DialogFragment {

    public static AddBoardFragment newInstance() {

        Bundle args = new Bundle();

        AddBoardFragment fragment = new AddBoardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_add_board, container, false);
        return view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Board> boardList = new ArrayList<>();

        //set name of boards is here
        boardList.add(new Board("First"));
        boardList.add(new Board("Second"));
        boardList.add(new Board("Third"));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        AddBoardAdapter adapter = new AddBoardAdapter(boardList);
        recyclerView.setAdapter(adapter);

        getDialog().setTitle("My title");
    }
}
