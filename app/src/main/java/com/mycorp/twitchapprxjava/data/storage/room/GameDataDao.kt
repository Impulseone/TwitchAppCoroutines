package com.mycorp.twitchapprxjava.data.storage.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.mycorp.twitchapprxjava.data.storage.model.GameDataTable
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface GameDataDao {
    @Insert(onConflict = REPLACE)
    fun insert(gameData: GameDataTable): Completable

    @Insert(onConflict = REPLACE)
    fun insertAll(objects: List<GameDataTable>): Completable

    @Update
    fun update(gameData: GameDataTable): Completable

    @Delete
    fun delete(gameData: GameDataTable): Completable

    @Query("SELECT * FROM GameDataTable")
    fun getAllGames(): Flowable<List<GameDataTable>>

    @Query("SELECT * FROM GameDataTable WHERE id=(:id)")
    fun getGameById(id: Int): Flowable<GameDataTable>
}