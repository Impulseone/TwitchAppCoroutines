package com.mycorp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mycorp.database.entities.GameFollowersEntity

@Dao
interface GameFollowersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gameFollowersEntity: GameFollowersEntity)

    @Query("SELECT * FROM GameFollowersEntity where gameId=:gameId")
    suspend fun getGameFollowersById(gameId: String): GameFollowersEntity
}