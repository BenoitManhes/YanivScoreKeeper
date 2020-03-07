package com.studiobeu.yaniv.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.studiobeu.yaniv.data.local.entity.Player;

import java.util.List;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM "+ Player.TABLE)
    List<Player> getAll();

    @Query("SELECT * FROM "+ Player.TABLE +" WHERE id IN (:playerId)")
    List<Player> loadAllByIds(Long[] playerId);

    @Query("SELECT * FROM "+ Player.TABLE +" WHERE id LIKE :playerId LIMIT 1")
    Player findById(Long playerId);

    @Insert
    void insert(Player player);

    @Insert
    void insertAll(Player... players);

    @Delete
    void delete(Player player);
}
