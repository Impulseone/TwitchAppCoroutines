package com.mycorp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mycorp.database.entities.FollowerInfoEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FollowersDao {
    @Insert(onConflict = REPLACE)
    fun insertAll(objects: List<FollowerInfoEntity>): Completable

    @Query("select * from FollowerInfoEntity where id in (:ids)")
    fun getByIds(ids: List<String>): Single<List<FollowerInfoEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insertAllSuspend(objects: List<FollowerInfoEntity>)

    @Query("select * from FollowerInfoEntity where id in (:ids)")
    suspend fun getByIdsSuspend(ids: List<String>): List<FollowerInfoEntity>
}