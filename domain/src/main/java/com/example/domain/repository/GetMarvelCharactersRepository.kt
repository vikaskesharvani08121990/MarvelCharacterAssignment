package com.example.domain.repository

import com.example.common.utils.network.NetworkStatus
import com.example.domain.model.CharacterDetails
import com.example.domain.model.CharacterList


interface GetMarvelCharactersRepository {

    suspend fun getMarvelCharacters(
        publicKey: String,
        privateKey: String,
        time: Long
    ): NetworkStatus<CharacterList>


    suspend fun getMarvelCharacterById(
        publicKey: String,
        privateKey: String,
        time: Long,
        characterId: Int
    ): NetworkStatus<CharacterDetails>
}