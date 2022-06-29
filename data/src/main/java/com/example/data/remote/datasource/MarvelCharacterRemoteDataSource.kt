package com.example.data.remote.datasource

import com.example.appcommon.utils.NetworkResponse
import com.example.data.entity.CharacterListResponse

interface MarvelCharacterRemoteDataSource {

    suspend  fun getMarvelCharacters(
        publicKey: String,
        hash: String,
        time: Long
    ): NetworkResponse<CharacterListResponse>

    suspend  fun getMarvelCharacterByCharacterId(
        publicKey: String,
        hash: String,
        time: Long,
        characterId:Int
    ): NetworkResponse<CharacterListResponse>

}