package com.example.news_v2.utils

import kotlin.random.Random

object NumberUtils {
    fun generateRandomId(): Long {
        val seed = System.currentTimeMillis()

        val random = Random(seed)
        val number =  random.nextLong() % 10000000000000
        return if (number < 0) -number else number
    }
}