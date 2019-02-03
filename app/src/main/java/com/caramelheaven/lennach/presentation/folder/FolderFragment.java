package com.caramelheaven.lennach.presentation.folder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caramelheaven.lennach.presentation.base.fragment.BaseFragment;

/**
 * Created by CaramelHeaven on 18:36, 03/02/2019.
 */
public class FolderFragment extends BaseFragment {

    public static FolderFragment newInstance() {

        Bundle args = new Bundle();

        FolderFragment fragment = new FolderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
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

    }

    @Override
    protected void deInitViews() {

    }
}
