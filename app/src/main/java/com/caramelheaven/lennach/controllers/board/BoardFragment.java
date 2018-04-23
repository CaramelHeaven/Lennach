package com.caramelheaven.lennach.controllers.board;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.caramelheaven.lennach.MyApplication;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.controllers.FullScreenImageFragment;
import com.caramelheaven.lennach.datasourse.model.Board;
import com.caramelheaven.lennach.datasourse.model.Threads;
import com.caramelheaven.lennach.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BoardFragment extends Fragment {

    private List<Threads> threadsList = new ArrayList<>();
    private BoardAdapter adapter;

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

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "зашли в фрагмент", Toast.LENGTH_SHORT).show();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        MyApplication.getInstance().getMainApiInterface().getBoard("b")
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.computation())
                .map(Board::getThreads)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(threads -> {
                    threadsList.addAll(threads);
                    for (Threads thread : threadsList) {
                        Log.d("LOGS", "ff: " + thread.getSubject());
                    }
                    adapter = new BoardAdapter(getContext(), threadsList);
                    recyclerView.setAdapter(adapter);

                    adapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            String passFileUrl = threadsList.get(position).getFiles().get(0).getPath();
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, FullScreenImageFragment.newInstance(passFileUrl))
                                    .addToBackStack(null)
                                    .commit();
                            Toast.makeText(getContext(), "pos: " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                }, error -> {
                });



    }
}
