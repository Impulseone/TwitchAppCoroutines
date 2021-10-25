package com.mycorp.twitchapprxjava.data.storage.model

data class GameItemDataDto(
	val total: Int? = null,
	val follows: List<FollowerDto?>? = null
)

data class FollowerDto(
	val created_at: String? = null,
	val user: User? = null,
	val notifications: Boolean? = null
)

data class User(
	val updated_at: String? = null,
	val name: String? = null,
	val bio: String? = null,
	val created_at: String? = null,
	val logo: String? = null,
	val id: String? = null,
	val display_name: String? = null,
	val type: String? = null
)

