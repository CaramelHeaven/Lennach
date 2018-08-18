package com.caramelheaven.lennach.ui.main.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.datasource.database.entity.helpers.PostFileThread;
import com.caramelheaven.lennach.datasource.model.BoardNavModel;
import com.caramelheaven.lennach.ui.base.BaseFragment;
import com.caramelheaven.lennach.ui.board.BoardFragment;
import com.caramelheaven.lennach.ui.main.navigation.add.AddDialogFragment;
import com.caramelheaven.lennach.ui.main.navigation.presenter.BoardNavigationPresenter;
import com.caramelheaven.lennach.ui.main.navigation.presenter.BoardNavigationView;
import com.caramelheaven.lennach.utils.myOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class BoardNavigationFragment extends MvpAppCompatFragment implements BoardNavigationView, BaseFragment,
        AddDialogFragment.AddDialogListener<BoardNavModel> {

    @InjectPresenter
    BoardNavigationPresenter presenter;

    private ProgressBar progressBar;
    private RecyclerView rvBase;
    private FloatingActionButton fabAdd;

    private BoardAdapter adapter;

    public static BoardNavigationFragment newInstance() {

        Bundle args = new Bundle();

        BoardNavigationFragment fragment = new BoardNavigationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar);
        rvBase = view.findViewById(R.id.rv_base);
        fabAdd = view.findViewById(R.id.fab_add_board_name);

        provideRecyclerAndAdapter();
        provideFab();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        progressBar = null;
        fabAdd = null;
        rvBase = null;
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
    public void provideRecyclerAndAdapter() {
        rvBase.setHasFixedSize(true);
        rvBase.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        //test
        List<BoardNavModel> test = new ArrayList<>();
        test.add(new BoardNavModel("pa"));
        test.add(new BoardNavModel("b"));
        test.add(new BoardNavModel("pr"));

        adapter = new BoardAdapter(test);
        rvBase.setAdapter(adapter);

        adapter.setMyOnItemClickListener(position ->
                getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,
                        BoardFragment.newInstance(adapter.getItemByPosition(position).getName()))
                .addToBackStack(null)
                .commit());
    }

    private void provideFab() {
        fabAdd.setOnClickListener(view -> {
            AddDialogFragment fragment = AddDialogFragment.newInstance();
            fragment.setTargetFragment(BoardNavigationFragment.this, 300);
            fragment.show(getActivity().getSupportFragmentManager(), null);
        });
    }

    @Override
    public void onFinish(List<BoardNavModel> models) {
        adapter.updateAdapter(models);
    }
}
