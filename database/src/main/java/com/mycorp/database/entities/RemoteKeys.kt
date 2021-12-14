package com.mycorp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteKeys(@PrimaryKey val gameId: String, val prevKey: Int?, val nextKey: Int?)
