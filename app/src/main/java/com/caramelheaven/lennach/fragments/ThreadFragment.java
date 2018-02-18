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
import com.caramelheaven.lennach.Utils.Constants;
import com.caramelheaven.lennach.adapters.ThreadAdapter;
import com.caramelheaven.lennach.data.SimplyBoard;
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
    private final int PAGE_SIZE = Constants.LIST_PAGE_SIZE;
    private int page = Constants.LIST_FIRST_PAGE;
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

        Log.d(LOGS, "Create recyclerView");
        recyclerView = view.findViewById(R.id.fragment_recyclerView_thread);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        threadAdapter = new ThreadAdapter(getActivity(), list);
        recyclerView.setAdapter(threadAdapter);

        if (list.size() < 1) {
            Log.d(LOGS, "Get Data from internet");
            getData();
        }
    }

    private void getData() {
        Log.d(LOGS, "Called getData() ");
        ApiFactory.getCheckingService()
                .getSimplyBoard("pa", "456782")
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.computation())
                .map(SimplyBoard::getThreads)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(threadRealms -> {
                    //It's a very interesting api :)
                    /*for (int i = 0; i < threadRealms.get(0).getPosts().size(); i++) {
                        Log.d(LOGS, "Size posts in thread: " + threadRealms.get(0).getPosts().size());
                        Log.d(LOGS, String.valueOf(threadRealms.get(0).getPosts().get(i).getComment()));
                    }*/
                    ThreadDbHelper dbHelper = new ThreadDbHelper(threadRealms.get(0).getPosts(), realmUI);
                    dbHelper.saveToDatabase();
                    Log.d(LOGS, "Try to set data with showList");
                    showList();
                });

    }

  /*  private void showError() {
        //textOnline.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
    }*/

    void showList() {
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
