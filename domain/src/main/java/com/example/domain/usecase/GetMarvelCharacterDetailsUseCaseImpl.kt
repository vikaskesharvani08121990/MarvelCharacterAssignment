package com.example.domain.usecase

import com.example.common.utils.network.NetworkStatus
import com.example.domain.model.MarvelCharacter
import com.example.domain.repository.GetMarvelCharactersRepository
import javax.inject.Inject

class GetMarvelCharacterDetailsUseCaseImpl @Inject constructor(private val repository: GetMarvelCharactersRepository) :
    GetMarvelCharacterDetailsUseCase {


    override suspend operator fun invoke(
        publicKey: String,
        privateKey: String,
        time: Long, characterId: Int
    ): NetworkStatus<MarvelCharacter> {
        return repository.getMarvelCharacterById(publicKey, privateKey, time, characterId)
    }

}