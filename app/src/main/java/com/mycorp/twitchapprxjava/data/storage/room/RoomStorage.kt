package com.mycorp.twitchapprxjava.data.storage.room

import android.content.Context
import com.mycorp.twitchapprxjava.data.storage.Storage
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.GameDataEntity
import io.reactivex.Single

class RoomStorage(context: Context) : Storage {

    private var db: AppDatabase = AppDatabase.getInstance(context = context)

    override fun getGamesDataFromDb(): Single<List<GameDataEntity>> {
        return db.gameDataDao.getAllGames()
    }

    override fun insertGamesData(gamesData: List<GameData>) = db.gameDataDao.insertAll(parseGameDataToGameDataTable(gamesData))


    private fun parseGameDataToGameDataTable(gamesData: List<GameData>): List<GameDataEntity> {
        val gamesDataEntities: MutableList<GameDataEntity> = mutableListOf()
        for (item in gamesData) {
            gamesDataEntities.add(
                GameDataEntity(
                    item.id,
                    item.name,
                    item.logoUrl,
                    item.channelsCount,
                    item.watchersCount
                )
            )
        }
        return gamesDataEntities
    }


}