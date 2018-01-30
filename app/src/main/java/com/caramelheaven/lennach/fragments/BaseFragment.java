package com.caramelheaven.lennach.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caramelheaven.lennach.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;

public abstract class BaseFragment<T> extends Fragment {
    protected RecyclerView recyclerView;
    protected RealmConfiguration realmConfiguration;
    protected Realm realmUI;
    protected RealmList<T> list = new RealmList<>();

    //abstract void getData();



    protected int getLayoutId() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //но у меня же он уже создается независимо?
        realmConfiguration = new RealmConfiguration.Builder().build();
        realmUI = Realm.getDefaultInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //Закрепление ресайклера за каждым фрагментом, который его унаследует
        View view = inflater.inflate(getLayoutId(), container, false);
        recyclerView = view.findViewById(R.id.fragment_recycler);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (realmUI != null) {
            realmUI.close();
        }
        //Хорошая практика что ли? Так пишут реалм продакшен
        recyclerView.setAdapter(null);
    }
}