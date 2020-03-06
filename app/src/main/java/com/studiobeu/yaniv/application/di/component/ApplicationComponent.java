package com.studiobeu.yaniv.injection.component;

import android.content.Context;

import com.studiobeu.yaniv.data.DataManager;
import com.studiobeu.yaniv.data.local.PreferenceService;
import com.studiobeu.yaniv.injection.ApplicationContext;
import com.studiobeu.yaniv.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

//    DataManager dataManager();

    @ApplicationContext
    Context context();

    PreferenceService preferenceService();

}
