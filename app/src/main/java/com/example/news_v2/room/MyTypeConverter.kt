package com.example.news_v2.room

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.news_v2.data.models.Source
import com.google.gson.Gson

@ProvidedTypeConverter
class MyTypeConverter {
    @TypeConverter
    fun appToString(source: Source): String  {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun stringToApp(string: String): Source {
        return Gson().fromJson(string, Source::class.java)
    }

}