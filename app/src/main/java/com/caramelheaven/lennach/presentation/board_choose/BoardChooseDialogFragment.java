package com.caramelheaven.lennach.presentation.board_choose;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.board_choose.presenter.BoardChoosePresenter;
import com.caramelheaven.lennach.presentation.board_choose.presenter.BoardChooseView;

/**
 * Created by CaramelHeaven on 19:24, 13/01/2019.
 */
public class BoardChooseDialogFragment extends MvpAppCompatDialogFragment implements BoardChooseView {

    private DisplayMetrics displayMetrics;

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
}
