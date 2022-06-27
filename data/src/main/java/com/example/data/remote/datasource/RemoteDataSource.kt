package com.example.data.remote.datasource

import com.example.common.utils.network.NetworkStatus
import com.example.data.entity.DataCharacterListDTO


interface RemoteDataSource {

    suspend  fun getMarvelCharacters(
        publicKey: String,
        hash: String,
        time: Long
    ): NetworkStatus<DataCharacterListDTO>


    suspend  fun getMarvelCharacterByCharacterId(
        publicKey: String,
        hash: String,
        time: Long,
        characterId:Int
    ): NetworkStatus<DataCharacterListDTO>



}