package com.studiobeu.yaniv.application.di.component;

import android.content.Context;

import com.studiobeu.yaniv.application.YanivApp;
import com.studiobeu.yaniv.application.di.module.RoomModule;
import com.studiobeu.yaniv.data.DataManager;
import com.studiobeu.yaniv.data.local.PreferenceService;
import com.studiobeu.yaniv.application.di.ApplicationContext;
import com.studiobeu.yaniv.application.di.module.ApplicationModule;
import com.studiobeu.yaniv.data.local.RoomService;
import com.studiobeu.yaniv.data.local.dao.PlayerDao;
import com.studiobeu.yaniv.data.local.database.AppDataBase;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, RoomModule.class})
public interface ApplicationComponent {

    void inject(YanivApp app);

    @ApplicationContext
    Context context();

    RoomService roomService();

    DataManager dataManager();
}
