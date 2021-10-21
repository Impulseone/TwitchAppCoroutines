package com.mycorp.twitchapprxjava.data.storage.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.mycorp.twitchapprxjava.data.storage.model.GameDataEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface GameDataDao {
    @Insert(onConflict = REPLACE)
    fun insert(gameData: GameDataEntity): Completable

    @Insert(onConflict = REPLACE)
    fun insertAll(objects: List<GameDataEntity>): Completable

    @Update
    fun update(gameData: GameDataEntity): Completable

    @Delete
    fun delete(gameData: GameDataEntity): Completable

    @Query("SELECT * FROM GameDataEntity")
    fun getAllGames(): Single<List<GameDataEntity>>

    @Query("SELECT * FROM GameDataEntity WHERE id=(:id)")
    fun getGameById(id: Int): Flowable<GameDataEntity>
}