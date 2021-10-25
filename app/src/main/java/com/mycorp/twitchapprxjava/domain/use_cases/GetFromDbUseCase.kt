package com.mycorp.twitchapprxjava.domain.use_cases

import com.mycorp.twitchapprxjava.domain.repository.Repository

class GetFromDbUseCase(private val repository: Repository) {
    fun getGamesData() = repository.getGamesDataFromDb()
    fun getFollowersData(id: String) = repository.getFollowersListFromDb(id)
}