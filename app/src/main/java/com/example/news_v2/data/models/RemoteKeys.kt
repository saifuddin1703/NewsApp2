package com.example.news_v2.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val articleId : Long,
    val nextKey : Int?,
    val prevKey : Int?
)
