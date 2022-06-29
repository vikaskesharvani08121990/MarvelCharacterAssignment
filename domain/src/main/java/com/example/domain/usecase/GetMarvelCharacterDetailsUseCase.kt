package com.example.domain.usecase

import com.example.appcommon.utils.NetworkResponse
import com.example.domain.model.MarvelCharacter

interface GetMarvelCharacterDetailsUseCase {

    suspend operator fun invoke(
        publicKey: String,
        privateKey: String,
        time: Long, characterId: Int
    ): NetworkResponse<MarvelCharacter>
}