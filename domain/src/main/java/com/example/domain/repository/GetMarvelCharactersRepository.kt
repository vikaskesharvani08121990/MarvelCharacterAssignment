package com.example.domain.repository

import com.example.common.utils.network.NetworkStatus
import com.example.domain.model.MarvelCharacter


interface GetMarvelCharactersRepository {

    suspend fun getMarvelCharacters(
        publicKey: String,
        privateKey: String,
        time: Long
    ): NetworkStatus<List<MarvelCharacter>>


    suspend fun getMarvelCharacterById(
        publicKey: String,
        privateKey: String,
        time: Long,
        characterId: Int
    ): NetworkStatus<MarvelCharacter>
}