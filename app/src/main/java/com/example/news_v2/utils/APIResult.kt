package com.example.news_v2.utils

data class APIResult<T>(val data : T?, val status : Status, val message : String?){
    companion object{
        fun <T> success(data: T?): APIResult<T> = APIResult(data = data, status = Status.SUCCESS, null)
        fun <T> error(message: String?): APIResult<T> =
            APIResult(data = null, status = Status.ERROR, message)

        fun <T> loading(): APIResult<T> = APIResult(data = null, status = Status.LOADING, null)
    }
}
