package com.example.data.utils


import com.example.appcommon.utils.CONNECTION_ERROR_CODE
import com.example.appcommon.utils.SOCKET_TIME_OUT_ERROR_CODE
import com.example.appcommon.utils.UNKNOWN_HOST_ERROR_CODE
import com.example.appcommon.utils.UNKNOWN_NETWORK_ERROR_CODE
import com.example.appcommon.utils.NetworkResponse
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): NetworkResponse<T> {
    try {
        val response = call.invoke()
        if (response.isSuccessful) {
            if (response.body() != null) {
                return NetworkResponse.Success(response.body())
            }
        }
        return NetworkResponse.Error(response.message())
    } catch (e: Exception) {
        return when (e) {
            is ConnectException -> {
                NetworkResponse.Error(errorCode = CONNECTION_ERROR_CODE)
            }
            is UnknownHostException -> {
                NetworkResponse.Error(errorCode = UNKNOWN_HOST_ERROR_CODE)
            }
            is SocketTimeoutException -> {
                NetworkResponse.Error(errorCode = SOCKET_TIME_OUT_ERROR_CODE)
            }
            is HttpException -> {
                NetworkResponse.Error(errorCode = UNKNOWN_NETWORK_ERROR_CODE)
            }
            else -> {
                NetworkResponse.Error(errorCode = UNKNOWN_NETWORK_ERROR_CODE)
            }
        }
    }
}