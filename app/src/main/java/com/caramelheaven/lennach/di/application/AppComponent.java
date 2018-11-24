package com.caramelheaven.lennach.di.application;

import com.caramelheaven.lennach.di.main.MainComponent;
import com.caramelheaven.lennach.di.main.MainModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {
    MainComponent plusMainComponent(MainModule mainModule);
}
