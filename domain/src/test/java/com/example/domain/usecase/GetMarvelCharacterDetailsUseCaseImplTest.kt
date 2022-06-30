package com.example.domain.usecase

import com.example.appcommon.utils.NetworkResponse
import com.example.domain.marvelCharacterDetails
import com.example.domain.model.MarvelCharacter
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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class GetMarvelCharacterDetailsUseCaseImplTest {

    @Mock
    lateinit var repository: MarvelCharactersRepository

    @Mock
    lateinit var useCase: GetMarvelCharacterDetailsUseCase
    
    @Test
    fun positiveTestGetMarvelCharacters() {
        CoroutineScope(Dispatchers.Default).launch {
             val characterId = 1017100
            val response = NetworkResponse.Success(data = marvelCharacterDetails)
            Mockito.`when`(
                repository.getMarvelCharacterById(
                    publicKey,
                    privateKey,
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
            Truth.assertThat(wantedResponse.data?.id == characterId).isTrue()
        }

    }

    @Test
    fun negativeTestForMarvelCharactersWhenPrivateAndPublicKeyIsInvalid() {
        CoroutineScope(Dispatchers.Default).launch {
            val characterId = 1017100
            val response = NetworkResponse.Error<MarvelCharacter>()
            Mockito.`when`(
                repository.getMarvelCharacterById(
                    "",
                    "",
                    System.currentTimeMillis(),
                    characterId
                )
            ).thenReturn(response)

            val wantedResponse =
                useCase.invoke(publicKey, privateKey, System.currentTimeMillis(), characterId)
            verify(repository, times(1)).getMarvelCharacterById(
                "",
                "",
                System.currentTimeMillis(),
                characterId
            )
            Truth.assertThat(wantedResponse.data == null).isTrue()
        }

    }

    @Test
    fun negativeTestForMarvelCharactersWhenCharacterIdIsInvalid() {
        CoroutineScope(Dispatchers.Default).launch {
            val characterId = 0
            val response = NetworkResponse.Error<MarvelCharacter>()
            Mockito.`when`(
                repository.getMarvelCharacterById(
                    publicKey,
                    privateKey,
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
            Truth.assertThat(wantedResponse.data == null).isTrue()
        }

    }

}