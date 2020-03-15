package com.studiobeu.yaniv.application.di.module;

import android.util.Log;

import com.studiobeu.yaniv.application.YanivApp;
import com.studiobeu.yaniv.data.local.RoomService;
import com.studiobeu.yaniv.data.local.dao.PlayerDao;
import com.studiobeu.yaniv.data.local.database.AppDataBase;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private AppDataBase dataBase;

    public RoomModule(YanivApp mApplication) {
        this.dataBase = Room.databaseBuilder(mApplication, AppDataBase.class, "app_db").build();
    }

    @Singleton
    @Provides
    RoomService providesRoomervice() {
        return new RoomService(dataBase);
    }

    @Singleton
    @Provides
    PlayerDao providesPlayerDao() {
        return this.dataBase.getPlayerDao();
    }

}
