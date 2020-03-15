package com.studiobeu.yaniv.application.di.module;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.studiobeu.yaniv.application.YanivApp;
import com.studiobeu.yaniv.data.DataManager;
import com.studiobeu.yaniv.data.local.PreferenceService;
import com.studiobeu.yaniv.application.di.ApplicationContext;
import com.studiobeu.yaniv.data.local.database.AppDataBase;

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
    DataManager provideDataManager(){
        return new DataManager(mApplication, new PreferenceService(mApplication));
    }

}