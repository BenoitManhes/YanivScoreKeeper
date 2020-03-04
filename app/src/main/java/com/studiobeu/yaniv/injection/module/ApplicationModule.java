package com.studiobeu.yaniv.injection.module;

import android.app.Application;
import android.content.Context;

import com.studiobeu.yaniv.injection.ApplicationContext;
import com.studiobeu.yaniv.ui.Global;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Provide application-level dependencies. Mainly singleton object that can be injected from
 * anywhere in the app.
 */
@Module
public class ApplicationModule {
    protected final Global mApplication;

    public ApplicationModule(Global application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

}