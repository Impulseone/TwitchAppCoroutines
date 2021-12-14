package com.mycorp.database.storage

import com.mycorp.database.dao.RemoteKeysDao
import com.mycorp.database.entities.RemoteKeys

class RemoteKeysStorageImplementation(private val remoteKeysDao: RemoteKeysDao) :
    RemoteKeysStorage {

    override suspend fun insertAll(remoteKeys: List<RemoteKeys>) =
        remoteKeysDao.insertAll(remoteKeys)

    override suspend fun remoteKeysGameId(id: String) =
        remoteKeysDao.remoteKeysGameId(id)

    override suspend fun clearRemoteKeys() = remoteKeysDao.clearRemoteKeys()
}