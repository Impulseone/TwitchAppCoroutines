package com.mycorp.twitchapprxjava.usecases

import com.mycorp.twitchapprxjava.repository.FavoriteGamesRepository
import com.mycorp.twitchapprxjava.repository.FollowersRepository
import com.mycorp.twitchapprxjava.repository.GamesRepository
import io.reactivex.Single

class GameDataUseCaseImpl(
    private val followersRepository: FollowersRepository,
    private val gamesRepository: GamesRepository,
    private val favoriteGamesRepository: FavoriteGamesRepository
) : GameDataUseCase {
    override fun getGames() = gamesRepository.getGamesData()

    override fun fetchGameData(gameId: String) = Single.just(gameId)
        .flatMap { id ->
            gamesRepository.getGameDataById(id)
                .flatMap { data ->
                    favoriteGamesRepository.checkIsFavorite(id).map {
                        it to data
                    }
                }
                .flatMap { (isFavorite, gameData) ->
                    followersRepository.fetchFollowers(id).map { list ->
                        Triple(isFavorite, gameData, list.size.toString())
                    }
                }
        }

    override fun getGameData(gameId: String) = Single.just(gameId)
        .flatMap { id ->
            gamesRepository.getGameDataById(id)
                .flatMap { data ->
                    favoriteGamesRepository.checkIsFavorite(id).map {
                        it to data
                    }
                }
                .flatMap { (isFavorite, gameData) ->
                    followersRepository.getFollowersByGameId(id).map { list ->
                        Triple(isFavorite, gameData, list.size.toString())
                    }
                }
        }
}