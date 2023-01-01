package com.example.news_v2.data.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class Article(
//    @SerializedName("id") val id : Int = 0,
    @SerializedName("source") val source: Source? = Source(),
    @SerializedName("author") val author: String? = "",
    @SerializedName("title") val title: String? = "",
    @SerializedName("description") val description: String? = "",
    @SerializedName("url") val url: String? = "",
    @SerializedName("urlToImage") val urlToImage: String? = "",
    @SerializedName("publishedAt") val publishedAt: String? = "",
    @SerializedName("content") val content : String? = ""

)
