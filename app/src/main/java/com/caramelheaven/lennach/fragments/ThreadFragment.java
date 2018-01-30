package com.caramelheaven.lennach.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.database.ThreadRealm;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThreadFragment extends BaseFragment<ThreadRealm> {


    public ThreadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thread, container, false);
    }

}
