package com.mycorp.twitchapprxjava.database

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