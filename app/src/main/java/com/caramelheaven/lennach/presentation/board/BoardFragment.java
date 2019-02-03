package com.caramelheaven.lennach.presentation.board;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.base.fragment.BaseFragment;

/**
 * Created by CaramelHeaven on 18:07, 03/02/2019.
 */
public class BoardFragment extends BaseFragment {

    private RelativeLayout relativeLayout;

    public static BoardFragment newInstance() {

        Bundle args = new Bundle();

        BoardFragment fragment = new BoardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void initViews(View view) {
        relativeLayout = view.findViewById(R.id.relativeLayout);
    }

    @Override
    protected void deInitViews() {
        relativeLayout = null;
    }
}
