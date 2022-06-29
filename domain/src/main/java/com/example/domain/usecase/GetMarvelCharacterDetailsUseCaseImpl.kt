package com.example.domain.usecase

import com.example.domain.repository.MarvelCharactersRepository
import javax.inject.Inject

class GetMarvelCharacterDetailsUseCaseImpl @Inject constructor(private val repository: MarvelCharactersRepository) :
    GetMarvelCharacterDetailsUseCase {

    override suspend operator fun invoke(
        publicKey: String,
        privateKey: String,
        time: Long, characterId: Int
    ) = repository.getMarvelCharacterById(publicKey, privateKey, time, characterId)

}