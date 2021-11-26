package com.mycorp.features.usecases

import com.mycorp.model.FollowerInfo
import com.mycorp.model.GameData
import io.reactivex.Single

interface GameDataUseCase {
    fun fetchGameData(gameId: String): Single<Triple<Int, GameData, List<FollowerInfo>>>
    fun getGameData(gameId: String): Single<Triple<Int, GameData, List<FollowerInfo>>>
}