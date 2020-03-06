package com.studiobeu.yaniv.application.di.component;

import android.content.Context;

import com.studiobeu.yaniv.data.local.PreferenceService;
import com.studiobeu.yaniv.application.di.ApplicationContext;
import com.studiobeu.yaniv.application.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    PreferenceService preferenceService();

}
