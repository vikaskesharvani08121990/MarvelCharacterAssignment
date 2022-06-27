package com.example.domain.repository

import com.example.common.utils.network.NetworkStatus
import com.example.domain.model.DomainMatcherCharacterListResponse



interface GetMarvelCharactersRepository {

    suspend fun getMarvelCharacters(
        publicKey: String,
        privateKey: String,
        time: Long
    ): NetworkStatus<DomainMatcherCharacterListResponse>


    suspend fun getMarvelCharactersById(
        publicKey: String,
        privateKey: String,
        time: Long,
        characterId:Int
    ): NetworkStatus<DomainMatcherCharacterListResponse>
}