package com.studiobeu.yaniv.data.local.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.studiobeu.yaniv.data.local.dao.PlayerDao;
import com.studiobeu.yaniv.data.local.entity.Player;

@Database(entities = {Player.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract PlayerDao playerDao();
}
