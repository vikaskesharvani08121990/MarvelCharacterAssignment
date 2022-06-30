package com.example.domain.repository

import com.example.common.utils.NetworkResponse
import com.example.domain.model.MarvelCharacter

interface MarvelCharactersRepository {

    suspend fun getMarvelCharacters(
        publicKey: String,
        privateKey: String,
        time: Long
    ): NetworkResponse<List<MarvelCharacter>>


    suspend fun getMarvelCharacterById(
        publicKey: String,
        privateKey: String,
        time: Long,
        characterId: Int
    ): NetworkResponse<MarvelCharacter>
}