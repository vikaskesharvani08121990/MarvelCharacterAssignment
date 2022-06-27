package com.example.data.di

import com.example.common.utils.BASE_URL
import com.example.data.remote.api.MarvelAPI

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    private var okHttpClient: OkHttpClient? = null


    /*
    * The method returns the Okhttp object
    * */
    @Provides
    @Singleton
    internal fun provideOkhttpClient(): OkHttpClient {
        okHttpClient = OkHttpClient.Builder()
            .build()

        return (okHttpClient)!!
    }

    /*
    * The method returns the Retrofit object
    * */
    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MarvelAPI {
        return retrofit.create(MarvelAPI::class.java)
    }


}