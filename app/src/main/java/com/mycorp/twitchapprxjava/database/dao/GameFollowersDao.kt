package com.mycorp.twitchapprxjava.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mycorp.twitchapprxjava.database.entities.GameFollowersEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface GameFollowersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gameFollowersEntity: GameFollowersEntity): Completable

    @Query("SELECT * FROM GameFollowersEntity where gameId=:gameId")
    fun getGameFollowersById(gameId: String): Single<GameFollowersEntity>
}