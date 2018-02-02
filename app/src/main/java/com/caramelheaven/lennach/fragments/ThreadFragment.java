package com.caramelheaven.lennach.fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.adapters.ThreadAdapter;
import com.caramelheaven.lennach.data.ThreadMy;
import com.caramelheaven.lennach.database.ThreadDbHelper;
import com.caramelheaven.lennach.database.ThreadRealm;
import com.caramelheaven.lennach.network.ApiFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThreadFragment extends BaseFragment<ThreadRealm> {

    private LinearLayoutManager layoutManager;
    private ThreadAdapter threadAdapter;
    private final int PAGE_SIZE = 1000;//временно
    private int page = 1;
    private static final String LOGS = ThreadFragment.class.getSimpleName();

    public ThreadFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thread, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.fragment_recyclerView_thread);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        threadAdapter = new ThreadAdapter(getActivity(), list);
        recyclerView.setAdapter(threadAdapter);

        if (list.size() < 1) {
            getData1();
        }
    }

    private void getData1() {
        Log.d(LOGS, "Called getData() " + page);
        if (isOnline()) {
            ApiFactory.getCheckingService()
                    .getRxThread("pr", "1127618")
                    .subscribeOn(Schedulers.io())
                    .subscribeOn(Schedulers.computation())
                    .map(ThreadMy::getPosts)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(threadRealms -> {
                        Log.i(LOGS, String.valueOf("Document: " + threadRealms.get(1).getPosts().get(1).getSubject()));
                        ThreadDbHelper dbHelper = new ThreadDbHelper(threadRealms, realmUI);
                        dbHelper.saveToDatabase();
                        showList();
                    }, throwable -> showError());
        }
    }

    private void showError() {
        //textOnline.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
    }

    void showList() {
        Log.i(LOGS, String.valueOf("SHOW LIST: " + list));
        ThreadDbHelper dbHelper = new ThreadDbHelper(list, realmUI);
        dbHelper.getFromDatabase(page++, PAGE_SIZE);
        threadAdapter.notifyDataSetChanged();
    }

    private boolean isOnline() {
        ConnectivityManager manager =
                //подойдет ли getActivity?
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
