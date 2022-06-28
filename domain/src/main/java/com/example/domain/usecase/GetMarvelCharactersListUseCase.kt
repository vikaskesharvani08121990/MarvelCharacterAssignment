package com.example.domain.usecase

import com.example.common.utils.network.NetworkStatus
import com.example.domain.model.CharacterList

interface GetMarvelCharactersListUseCase {
    suspend operator fun invoke(
        publicKey: String,
        privateKey: String,
        time: Long
    ): NetworkStatus<CharacterList>
}