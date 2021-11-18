package com.mycorp.twitchapprxjava.usecases

import com.mycorp.twitchapprxjava.models.GameData
import io.reactivex.Single

interface GetGameDataUseCase {
    fun getGameData(gameId:String): Single<Triple<Int,GameData,String>>
}