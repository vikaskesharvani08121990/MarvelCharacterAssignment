package com.example.data.remote.datasource

import com.example.common.utils.network.NetworkStatus
import com.example.data.entity.CharacterListResponse


interface RemoteDataSource {

    suspend  fun getMarvelCharacters(
        publicKey: String,
        hash: String,
        time: Long
    ): NetworkStatus<CharacterListResponse>


    suspend  fun getMarvelCharacterByCharacterId(
        publicKey: String,
        hash: String,
        time: Long,
        characterId:Int
    ): NetworkStatus<CharacterListResponse>



}