package com.example.news_v2.utils

import com.google.gson.Gson

class Json {
    fun <A> String.fromJson(type: Class<A>): A {
        return Gson().fromJson(this,type)
    }

    fun String.toJson(): String? {
        return Gson().toJson(this)
    }
}