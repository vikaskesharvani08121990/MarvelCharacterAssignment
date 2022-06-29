package com.example.data.remote.datasource

import com.example.common.utils.network.NetworkStatus
import com.example.data.entity.CharacterListResponse
import com.example.data.remote.api.MarvelAPI
import com.example.data.utils.safeApiCall

class MarvelCharacterRemoteDataSourceImpl(private val apiService: MarvelAPI) :
    MarvelCharacterRemoteDataSource {

    override suspend fun getMarvelCharacters(
        publicKey: String,
        hash: String,
        time: Long
    ): NetworkStatus<CharacterListResponse> {
        return safeApiCall { apiService.getMarvelCharacters(publicKey, hash, time.toString()) }
    }

    override suspend fun getMarvelCharacterByCharacterId(
        publicKey: String,
        hash: String,
        time: Long,
        characterId: Int
    ): NetworkStatus<CharacterListResponse> {
        return safeApiCall {
            apiService.getMarvelCharacterByCharacterId(
                characterId,
                publicKey,
                hash,
                time.toString()
            )
        }
    }
}