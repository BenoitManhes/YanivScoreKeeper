package com.studiobeu.yaniv.application.di.module;

import android.app.Application;
import android.content.Context;

import com.studiobeu.yaniv.application.YanivApp;
import com.studiobeu.yaniv.data.local.PreferenceService;
import com.studiobeu.yaniv.application.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Provide application-level dependencies. Mainly singleton object that can be injected from
 * anywhere in the app.
 */
@Module
public class ApplicationModule {
    protected final YanivApp mApplication;

    public ApplicationModule(YanivApp application) {
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

    @Singleton
    @Provides
    PreferenceService providePreferenceManager(){
        return new PreferenceService(mApplication);
    }

}