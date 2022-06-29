package com.example.common.utils.network



sealed class NetworkStatus<T>(var data: T? = null, val errorMessage: String? = null,val errorCode: Int=0) {
    class Success<T>(data: T?) : NetworkStatus<T>(data)
    class Error<T>(errorMessage: String?=null,errorCode: Int=0, data: T? = null) : NetworkStatus<T>(data, errorMessage,errorCode)
    class Loading<T>(data: T? = null) : NetworkStatus<T>(data)
}