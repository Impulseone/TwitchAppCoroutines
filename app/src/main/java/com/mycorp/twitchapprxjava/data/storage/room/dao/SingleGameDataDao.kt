package com.mycorp.twitchapprxjava.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mycorp.twitchapprxjava.data.storage.room.entities.SingleGameDataEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SingleGameDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(singleGameDataEntity: SingleGameDataEntity): Completable

    @Query("SELECT * FROM SingleGameDataEntity where id=:id")
    fun getById(id: String): Single<SingleGameDataEntity>
}