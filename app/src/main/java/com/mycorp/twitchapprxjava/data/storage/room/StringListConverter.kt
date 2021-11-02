package com.mycorp.twitchapprxjava.data.storage.room

import androidx.room.TypeConverter

class StringListConverter {

    @TypeConverter
    fun fromListToString(stringsList: List<String>): String {
        return stringsList.joinToString()
    }

    @TypeConverter
    fun toListFromString(data: String): List<String> = if (data == "") mutableListOf()
    else data.split(",").map { it.trim() }
}