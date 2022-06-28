package com.example.domain.usecase

import com.example.common.utils.network.NetworkStatus
import com.example.domain.model.CharacterDetails
import com.example.domain.model.CharacterList
import com.example.domain.repository.GetMarvelCharactersRepository
import javax.inject.Inject

class GetMarvelCharacterDetailsUseCaseImpl @Inject constructor(private val repository: GetMarvelCharactersRepository):GetMarvelCharacterDetailsUseCase {


    override suspend operator fun invoke(
        publicKey: String,
        privateKey: String,
        time: Long, characterId: Int
    ): NetworkStatus<CharacterDetails> {
        return repository.getMarvelCharacterById(publicKey, privateKey, time, characterId)
    }

}