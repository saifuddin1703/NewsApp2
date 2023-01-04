package com.example.news_v2.data.models

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id") val id : String? = "",
    @SerializedName("name") val name : String? = ""
)
