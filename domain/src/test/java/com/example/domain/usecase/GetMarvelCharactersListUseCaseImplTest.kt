package com.example.domain.usecase


import com.example.appcommon.utils.MD5HashKey
import com.example.appcommon.utils.NetworkResponse
import com.example.domain.marvelCharacterList
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
class GetMarvelCharactersListUseCaseImplTest {

    @Mock
    lateinit var repository: MarvelCharactersRepository

    @Mock
    lateinit var useCase: GetMarvelCharactersListUseCase


    @Test
    fun getMarvelCharacters() {
        CoroutineScope(Dispatchers.Default).launch {
            val hash = MD5HashKey().getHash(publicKey, privateKey, System.currentTimeMillis())
            val response = NetworkResponse.Success(data = marvelCharacterList)
            Mockito.`when`(
                repository.getMarvelCharacters(
                    publicKey,
                    hash,
                    System.currentTimeMillis()
                )
            ).thenReturn(response)

            val wantedResponse = useCase.invoke(publicKey, privateKey, System.currentTimeMillis())
            verify(repository, times(1)).getMarvelCharacters(
                publicKey,
                hash,
                System.currentTimeMillis()
            )
            Truth.assertThat(wantedResponse.data != null).isTrue()
            Truth.assertThat(wantedResponse.data?.size == 2).isTrue()
        }

    }
}