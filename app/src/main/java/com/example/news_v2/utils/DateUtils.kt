package com.example.news_v2.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import java.util.concurrent.TimeUnit

object DateUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDate(date : String): String {
        var convTime: String? = null

        val suffix = "Ago"

        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
            val instant = Instant.parse(date)
            val pasTime = Date.from(instant)
//            val pasTime = dateFormat.parse(dataDate)
            val nowTime = Date()
            val dateDiff = nowTime.time - pasTime.time
            val second: Long = TimeUnit.MILLISECONDS.toSeconds(dateDiff)
            val minute: Long = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
            val hour: Long = TimeUnit.MILLISECONDS.toHours(dateDiff)
            val day: Long = TimeUnit.MILLISECONDS.toDays(dateDiff)
            if (second < 60) {
                convTime = "$second Seconds $suffix"
            } else if (minute < 60) {
                convTime = "$minute Minutes $suffix"
            } else if (hour < 24) {
                convTime = "$hour Hours $suffix"
            } else if (day >= 7) {
                convTime = if (day > 360) {
                    (day / 360).toString() + " Years " + suffix
                } else if (day > 30) {
                    (day / 30).toString() + " Months " + suffix
                } else {
                    (day / 7).toString() + " Week " + suffix
                }
            } else if (day < 7) {
                convTime = "$day Days $suffix"
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            Log.e("ConvTimeE", e.message.toString())
        }

        return convTime!!
    }
}