package com.example.domain.usecase

import com.example.domain.repository.MarvelCharactersRepository

class GetMarvelCharactersListUseCaseImpl (private val repository: MarvelCharactersRepository) :
    GetMarvelCharactersListUseCase {

    override suspend operator fun invoke(
        publicKey: String,
        privateKey: String,
        time: Long
    ) = repository.getMarvelCharacters(publicKey, privateKey, time)

}