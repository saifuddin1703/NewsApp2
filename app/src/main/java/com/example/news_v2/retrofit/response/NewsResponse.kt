package com.example.news_v2.retrofit.response

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status") val status : String = "",
    @SerializedName("totalResults") val totalResults : Int = 0,
    @SerializedName("articles") val articles : JsonArray = JsonArray()
)
