package com.mycorp.database.storage

import com.mycorp.database.entities.RemoteKeys

interface RemoteKeysStorage {
    suspend fun insertAll(remoteKeys: List<RemoteKeys>)

    suspend fun remoteKeysGameId(id: String): RemoteKeys?

    suspend fun clearRemoteKeys()
}