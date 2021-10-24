package com.mycorp.twitchapprxjava.data.storage.model

data class GameItemDataDto(
	val total: Int? = null,
	val follows: List<FollowsItem?>? = null
)

data class FollowsItem(
	val createdAt: String? = null,
	val user: User? = null,
	val notifications: Boolean? = null
)

data class User(
	val updatedAt: String? = null,
	val name: String? = null,
	val bio: String? = null,
	val createdAt: String? = null,
	val logo: String? = null,
	val id: String? = null,
	val displayName: String? = null,
	val type: String? = null
)

