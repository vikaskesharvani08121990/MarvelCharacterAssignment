package com.example.domain.usecase

import com.example.common.utils.MD5HashKey
import com.example.common.utils.network.NetworkStatus
import com.example.domain.characterId
import com.example.domain.marvelCharacterDetails
import com.example.domain.privateKey
import com.example.domain.publicKey
import com.example.domain.repository.MarvelCharactersRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class GetMarvelCharacterDetailsUseCaseImplTest {

    @Mock
    lateinit var repository: MarvelCharactersRepository

     @InjectMocks
    lateinit var useCase: GetMarvelCharacterDetailsUseCase


    @Test
    fun getMarvelCharacters() {
        CoroutineScope(Dispatchers.Default).launch {

            val hash = MD5HashKey().getHash(publicKey, privateKey, System.currentTimeMillis())
            val response = NetworkStatus.Success(data = marvelCharacterDetails)
            Mockito.`when`(
                repository.getMarvelCharacterById(
                    publicKey,
                    hash,
                    System.currentTimeMillis(),
                    characterId
                )
            ).thenReturn(response)

            val wantedResponse =
                useCase.invoke(publicKey, privateKey, System.currentTimeMillis(), characterId)
            verify(repository, times(1)).getMarvelCharacterById(
                publicKey,
                privateKey,
                System.currentTimeMillis(),
                characterId
            )
            Truth.assertThat(wantedResponse.data != null).isTrue()
            Truth.assertThat(wantedResponse.data?.id == 1017100).isTrue()
        }

    }
}