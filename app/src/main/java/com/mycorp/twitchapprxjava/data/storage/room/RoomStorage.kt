package com.mycorp.twitchapprxjava.data.storage.room

import android.content.Context
import com.mycorp.twitchapprxjava.data.storage.Storage
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.GameDataTable
import io.reactivex.Flowable

class RoomStorage(context: Context) : Storage {

    private var db: AppDatabase = AppDatabase.getInstance(context = context)

    override fun getGamesDataFromDb(): Flowable<List<GameDataTable>> {
        return db.gameDataDao.getAllGames()
    }

    override fun insertGamesData(gamesData: List<GameData>) = db.gameDataDao.insertAll(parseGameDataToGameDataTable(gamesData))


    private fun parseGameDataToGameDataTable(gamesData: List<GameData>): List<GameDataTable> {
        val gamesDataTables: MutableList<GameDataTable> = mutableListOf()
        for (item in gamesData) {
            gamesDataTables.add(
                GameDataTable(
                    item.id,
                    item.name,
                    item.logoUrl,
                    item.channelsCount,
                    item.watchersCount
                )
            )
        }
        return gamesDataTables
    }


}