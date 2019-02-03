package com.caramelheaven.lennach.di.main

import com.caramelheaven.lennach.presentation.main.presenter.MainPresenter
import dagger.Subcomponent

/**
 * Created by CaramelHeaven on 16:55, 03/02/2019.
 */
@MainScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun inject(mainPresenter: MainPresenter)
}
