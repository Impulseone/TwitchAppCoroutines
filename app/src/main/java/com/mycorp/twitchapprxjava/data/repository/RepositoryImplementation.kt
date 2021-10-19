package com.mycorp.twitchapprxjava.data.repository

import com.mycorp.twitchapprxjava.data.network.NetworkController
import com.mycorp.twitchapprxjava.data.storage.Storage
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.GameDataTable
import com.mycorp.twitchapprxjava.data.storage.model.TwitchResponse
import com.mycorp.twitchapprxjava.domain.repository.Repository
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

class RepositoryImplementation(
    private val networkController: NetworkController,
    private val storage: Storage
) : Repository {

    override fun getGamesDataFromNetwork(): Single<List<GameData>> {
        val gameData: Single<List<GameData>> =
            networkController.getDataFromNetwork().map { it: TwitchResponse ->
                parseTwitchResponseToGameData(it)
            }
        return gameData
    }

    private fun parseTwitchResponseToGameData(response: TwitchResponse): List<GameData> {
        val gamesData: MutableList<GameData> = mutableListOf()
        for (item in response.top!!) {
            gamesData.add(
                GameData(
                    item?.game?.id!!,
                    item.game.name!!,
                    item.game.box?.large!!,
                    item.channels!!,
                    item.viewers!!
                )
            )
        }
        return gamesData
    }

    override fun getGamesDataFromDb(): Single<List<GameData>> {
        val gameData:Single<List<GameData>> = storage.getGamesDataFromDb().map {
            parseGameDataTableToGameData(it)
        }
        return gameData
    }

    private fun parseGameDataTableToGameData(gamesDataTables: List<GameDataTable>): List<GameData> {
        val gamesData: MutableList<GameData> = mutableListOf()
        for (item in gamesDataTables) {
            gamesData.add(
                GameData(
                    item.id,
                    item.name,
                    item.logoUrl,
                    item.channelsCount,
                    item.watchersCount
                )
            )
        }
        return gamesData
    }

    override fun insertGamesDataToDb(gameDataTables: List<GameData>) =
        storage.insertGamesData(gamesData = gameDataTables)
}