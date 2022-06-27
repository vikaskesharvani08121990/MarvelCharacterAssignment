package com.example.data.remote.datasource

import com.example.common.utils.network.NetworkStatus
import com.example.data.entity.DataCharacterListDTO
import com.example.data.remote.api.MarvelAPI
import com.example.data.utils.safeApiCall


class RemoteDataSourceImpl (private val apiService: MarvelAPI): RemoteDataSource {


    override suspend fun getMarvelCharacters(
        publicKey: String,
        hash: String,
        time: Long
    ): NetworkStatus<DataCharacterListDTO> {
       return safeApiCall{apiService.getMarvelCharacters(publicKey,hash,time.toString())}
    }

    override suspend fun getMarvelCharacterByCharacterId(
        publicKey: String,
        hash: String,
        time: Long,
        characterId: Int
    ): NetworkStatus<DataCharacterListDTO> {
        return safeApiCall{apiService.getMarvelCharacterByCharacterId(characterId,publicKey,hash,time.toString())}
    }
}