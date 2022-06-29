package com.example.data.utils


import com.example.common.utils.CONNECT_EXCEPTION_CODE
import com.example.common.utils.SOCKET_TIME_OUT_EXCEPTION_CODE
import com.example.common.utils.UNKNOWN_HOST_EXCEPTION_CODE
import com.example.common.utils.UNKNOWN_NETWORK_EXCEPTION_CODE
import com.example.common.utils.network.NetworkStatus
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): NetworkStatus<T> {
    try {
        val response = call.invoke()
        if (response.isSuccessful) {
            if (response.body() != null) {
                return NetworkStatus.Success(response.body())
            }
        }
        return NetworkStatus.Error(response.message())
    } catch (e: Exception) {
        return when (e) {
            is ConnectException -> {
                NetworkStatus.Error(errorCode = CONNECT_EXCEPTION_CODE)
            }
            is UnknownHostException -> {
                NetworkStatus.Error(errorCode = UNKNOWN_HOST_EXCEPTION_CODE)
            }
            is SocketTimeoutException -> {
                NetworkStatus.Error(errorCode = SOCKET_TIME_OUT_EXCEPTION_CODE)
            }
            is HttpException -> {
                NetworkStatus.Error(errorCode = UNKNOWN_NETWORK_EXCEPTION_CODE)
            }
            else -> {
                NetworkStatus.Error(errorCode = UNKNOWN_NETWORK_EXCEPTION_CODE)
            }
        }
    }
}