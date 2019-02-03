package com.caramelheaven.lennach.di.application

import dagger.Component
import javax.inject.Singleton

/**
 * Created by CaramelHeaven on 16:48, 03/02/2019.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    //fun plusMainComponent(mainModule: MainModule): MainComponent
}
