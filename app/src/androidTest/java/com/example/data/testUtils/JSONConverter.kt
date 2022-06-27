package com.example.data.testUtils

import com.google.gson.Gson

class JSONConverter<T> {
    fun objectToJson(t: T): String {
        val gson = Gson()
        return gson.toJson(t)
    }

    fun jsonToObject(data: String?, t: Class<T>?): T {
        val gson = Gson()
        return gson.fromJson(data, t)
    }


}