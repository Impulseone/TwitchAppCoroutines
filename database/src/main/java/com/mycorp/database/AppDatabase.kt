package com.mycorp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mycorp.database.dao.*
import com.mycorp.database.entities.*

@Database(
    entities = [GameDataEntity::class, FollowerInfoEntity::class, FavoriteGameDataEntity::class, GameFollowersEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val gameDataDao: GameDataDao

    abstract val followersDao: FollowersDao

    abstract val favoriteGameDataDao: FavoriteGameDataDao

    abstract val gameFollowersDao: GameFollowersDao
}