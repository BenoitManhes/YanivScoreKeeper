package com.studiobeu.yaniv.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import com.studiobeu.yaniv.data.local.entity.Player;

import java.util.List;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM "+ Player.TABLE)
    Maybe<List<Player>> getAll();

    @Query("SELECT * FROM "+ Player.TABLE +" WHERE id IN (:playerId)")
    Maybe<List<Player>> loadAllByIds(Long[] playerId);

    @Query("SELECT * FROM "+ Player.TABLE +" WHERE id LIKE :playerId LIMIT 1")
    Maybe<Player> findById(Long playerId);

    @Query("SELECT MAX(id) FROM "+ Player.TABLE)
    Maybe<Long> getMaxId();

    @Insert
    Completable insert(Player player);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Player... players);

    @Delete
    void delete(Player player);
}
