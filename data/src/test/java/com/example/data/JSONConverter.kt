package com.example.data

import com.google.gson.Gson

class JSONConverter<T> {

    fun jsonToObject(data: String?, t: Class<T>?): T {
        val gson = Gson()
        return gson.fromJson(data, t)
    }


}