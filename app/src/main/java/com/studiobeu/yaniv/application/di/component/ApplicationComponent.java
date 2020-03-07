package com.studiobeu.yaniv.application.di.component;

import android.content.Context;

import com.studiobeu.yaniv.application.YanivApp;
import com.studiobeu.yaniv.data.local.PreferenceService;
import com.studiobeu.yaniv.application.di.ApplicationContext;
import com.studiobeu.yaniv.application.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(YanivApp app);

    @ApplicationContext
    Context context();

    PreferenceService preferenceService();
}
