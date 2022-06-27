package com.example.domain.usecase

import com.example.common.utils.network.NetworkStatus
import com.example.domain.model.DomainMatcherCharacterListResponse
import com.example.domain.repository.GetMarvelCharactersRepository
import javax.inject.Inject

class GetMarvelCharactersListUseCase  @Inject constructor(private val repository: GetMarvelCharactersRepository) {




    suspend operator fun invoke( publicKey: String,
                         privateKey: String,
                         time: Long): NetworkStatus<DomainMatcherCharacterListResponse> {
        return repository.getMarvelCharacters(publicKey,privateKey,time)
    }

}