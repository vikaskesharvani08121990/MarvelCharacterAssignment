package com.example.domain.usecase

import com.example.common.utils.network.NetworkStatus
import com.example.domain.model.DomainMatcherCharacterListResponse
import com.example.domain.repository.GetMarvelCharactersRepository
import javax.inject.Inject

class GetMarvelCharacterDetailsUseCase @Inject constructor(private val repository: GetMarvelCharactersRepository) {




    suspend operator fun invoke( publicKey: String,
                                 privateKey: String,
                                 time: Long,characterId:Int): NetworkStatus<DomainMatcherCharacterListResponse> {
        return repository.getMarvelCharactersById(publicKey,privateKey,time,characterId)
    }

}