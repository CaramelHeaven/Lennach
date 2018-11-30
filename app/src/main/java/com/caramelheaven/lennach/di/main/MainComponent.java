package com.caramelheaven.lennach.di.main;

import com.caramelheaven.lennach.presentation.main.MainActivity;
import com.caramelheaven.lennach.presentation.main.presenter.MainPresenter;

import dagger.Subcomponent;

@MainScope
@Subcomponent(modules = {MainModule.class})
public interface MainComponent {

    void inject(MainPresenter presenter);
}
