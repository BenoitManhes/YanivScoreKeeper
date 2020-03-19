package com.studiobeu.yaniv.data.local;

import com.studiobeu.yaniv.data.local.dao.PlayerDao;
import com.studiobeu.yaniv.data.local.database.AppDataBase;
import com.studiobeu.yaniv.data.local.entity.Player;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Singleton
public class RoomService {

    private final AppDataBase appDataBase;
    private final PlayerDao playerDao;

    @Inject
    public RoomService(AppDataBase appDataBase){
        this.appDataBase = appDataBase;
        this.playerDao = appDataBase.getPlayerDao();
    }

    /* ======================== Player ============================== */
    public Maybe<List<Player>> getAllPlayer(){
        return this.playerDao.getAll();
    }

    public Completable createPlayer(Player player){ return this.playerDao.insert(player);}

    public Completable deletePlayer(Player player){ return this.playerDao.delete(player);}

    public Maybe<Long> getNewPlayerId(){
        return this.playerDao.getMaxId();
    }
}
