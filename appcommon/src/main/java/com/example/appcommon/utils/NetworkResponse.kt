package com.example.appcommon.utils



sealed class NetworkResponse<T>(var data: T? = null, val errorMessage: String? = null, val errorCode: Int=0) {
    class Success<T>(data: T?) : NetworkResponse<T>(data)
    class Error<T>(errorMessage: String?=null,errorCode: Int=0, data: T? = null) : NetworkResponse<T>(data, errorMessage,errorCode)
    class Loading<T>(data: T? = null) : NetworkResponse<T>(data)
}