package com.mycorp.twitchapprxjava.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mycorp.twitchapprxjava.data.storage.room.entities.FollowerInfoEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FollowersDao {
    @Insert(onConflict = REPLACE)
    fun insertAll(objects: List<FollowerInfoEntity>): Completable

    @Query("select * from FollowerInfoEntity where id in (:ids)")
    fun getByIds(ids: List<String>): Single<List<FollowerInfoEntity>>
}