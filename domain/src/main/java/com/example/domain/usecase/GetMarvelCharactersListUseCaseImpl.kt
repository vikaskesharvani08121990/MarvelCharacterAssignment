package com.example.domain.usecase

import com.example.common.utils.network.NetworkStatus
import com.example.domain.model.CharacterList
import com.example.domain.repository.GetMarvelCharactersRepository
import javax.inject.Inject

class GetMarvelCharactersListUseCaseImpl @Inject constructor(private val repository: GetMarvelCharactersRepository) :
    GetMarvelCharactersListUseCase {


    override suspend operator fun invoke(
        publicKey: String,
        privateKey: String,
        time: Long
    ): NetworkStatus<CharacterList> {
        return repository.getMarvelCharacters(publicKey, privateKey, time)
    }

}