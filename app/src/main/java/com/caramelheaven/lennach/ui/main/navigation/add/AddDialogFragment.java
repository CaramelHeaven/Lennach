package com.caramelheaven.lennach.ui.main.navigation.add;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.datasource.model.BoardNavModel;
import com.caramelheaven.lennach.ui.base.BaseFragment;
import com.caramelheaven.lennach.ui.main.navigation.add.presenter.AddDialogPresenter;
import com.caramelheaven.lennach.ui.main.navigation.add.presenter.AddDialogView;
import com.caramelheaven.lennach.utils.myOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class AddDialogFragment extends MvpAppCompatDialogFragment implements BaseFragment, AddDialogView {

    private RecyclerView rvBoards;
    private ProgressBar progressBar;

    private AddDialogAdapter adapter;
    private DisplayMetrics displayMetrics;

    @InjectPresenter
    AddDialogPresenter presenter;

    public static AddDialogFragment newInstance() {

        Bundle args = new Bundle();

        AddDialogFragment fragment = new AddDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);
        return inflater.inflate(R.layout.fragment_add_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvBoards = view.findViewById(R.id.rv_boards);
        progressBar = view.findViewById(R.id.progressBar);
        displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        provideRecyclerAndAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rvBoards = null;
        progressBar = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(displayMetrics.widthPixels, displayMetrics.heightPixels / 2);
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public void provideRecyclerAndAdapter() {
        rvBoards.setHasFixedSize(true);
        rvBoards.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new AddDialogAdapter(new ArrayList<>());
        rvBoards.setAdapter(adapter);

        adapter.setMyOnItemClickListener(new myOnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                BoardNavModel board = adapter.getItemByPosition(position);
                Toast.makeText(getActivity(), "pos: " + board.getName(), Toast.LENGTH_SHORT).show();
                sendBackResult();
            }
        });
    }

    @Override
    public void showItems(List<BoardNavModel> models) {
        Timber.d("models> " + models.size());
        adapter.updateAdapter(models);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    public void sendBackResult() {
        AddDialogListener listener = (AddDialogListener) getTargetFragment();
        listener.onFinish(adapter.getItems());
        dismiss();
    }

    public interface AddDialogListener<T> {
        void onFinish(List<T> models);
    }
}