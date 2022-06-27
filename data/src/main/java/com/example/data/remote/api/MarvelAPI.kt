package com.example.data.remote.api

import com.example.data.entity.DataCharacterListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {
    @GET("/v1/public/characters")
    suspend fun getMarvelCharacters(@Query("apikey") apikey:String,
                                   @Query("hash") hash:String,
                                   @Query("ts") ts:String):Response<DataCharacterListDTO>


    @GET("/v1/public/characters/{characterId}")
    suspend fun getMarvelCharacterByCharacterId(@Path("characterId") characterId:Int,@Query("apikey") apikey:String,
                                    @Query("hash") hash:String,
                                    @Query("ts") ts:String):Response<DataCharacterListDTO>

}