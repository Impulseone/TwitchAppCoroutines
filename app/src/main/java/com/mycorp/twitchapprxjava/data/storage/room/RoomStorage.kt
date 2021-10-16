package com.mycorp.twitchapprxjava.data.storage.room

import android.content.Context
import com.mycorp.twitchapprxjava.data.storage.Storage
import com.mycorp.twitchapprxjava.data.storage.model.GameDataTable
import io.reactivex.Flowable

class RoomStorage(context: Context) : Storage {

    private var db: AppDatabase = AppDatabase.getInstance(context = context)

    override fun getGamesDataFromDb(): Flowable<List<GameDataTable>> {
       val flowable: Flowable<List<GameDataTable>> = db.gameDataDao.getAllGames()
        return flowable
    }

    override fun insertGamesData(gamesData: List<GameDataTable>) =
        db.gameDataDao.insertAll(gamesData)
}