package com.app.taiye.todocleanarchitecture.util

import androidx.room.TypeConverter
import com.app.taiye.todocleanarchitecture.data.Priority

class Converter {

    @TypeConverter
    fun fromPriority(priority: Priority):String{
        return priority.name
    }


    @TypeConverter
    fun toPriority(priority: String):Priority{
        return Priority.valueOf(priority)
    }
}