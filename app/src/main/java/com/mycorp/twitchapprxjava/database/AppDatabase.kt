package com.mycorp.twitchapprxjava.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mycorp.twitchapprxjava.database.dao.FavoriteGameDataDao
import com.mycorp.twitchapprxjava.database.dao.FollowersDao
import com.mycorp.twitchapprxjava.database.dao.GameDataDao
import com.mycorp.twitchapprxjava.database.dao.GameFollowersDao
import com.mycorp.twitchapprxjava.database.entities.FavoriteGameDataEntity
import com.mycorp.twitchapprxjava.database.entities.FollowerInfoEntity
import com.mycorp.twitchapprxjava.database.entities.GameDataEntity
import com.mycorp.twitchapprxjava.database.entities.GameFollowersEntity

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