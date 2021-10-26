package com.mycorp.twitchapprxjava.data.storage.room

import androidx.room.TypeConverter

class StringListConverter {

    @TypeConverter
    fun fromHobbies(hobbies: List<String>): String {
        return hobbies.joinToString()
    }

    @TypeConverter
    fun toHobbies(data: String): List<String> {
        return data.split(",").map { it.trim() }
    }
}