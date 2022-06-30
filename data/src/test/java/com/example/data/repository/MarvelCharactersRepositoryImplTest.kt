package com.example.data.repository


import com.example.appcommon.utils.MD5HashKey
import com.example.appcommon.utils.NetworkResponse
import com.example.data.GetRepositoryMockDataFromStringJson
import com.example.data.entity.CharacterListResponse
import com.example.data.remote.datasource.MarvelCharacterRemoteDataSource
import com.example.domain.repository.MarvelCharactersRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class MarvelCharactersRepositoryImplTest {

    @Mock
    lateinit var marvelCharacterRemoteDataSource: MarvelCharacterRemoteDataSource

    @Mock
    lateinit var repository: MarvelCharactersRepository

    private var dataObject: CharacterListResponse? = null

    private val publicKey = "74674dhr56tuu79fb001de15ad03tbshyr"
    private val privateKey = "75647er55s55a83dd9872d502bec06745"

    @Before
    fun setUp() {
        dataObject = GetRepositoryMockDataFromStringJson.getOneMarvelCharacterList()

    }

    @After
    fun tearDown() {
        dataObject = null
    }

    @Test
    fun positiveTestGetMarvelCharacters() {
        CoroutineScope(Dispatchers.Default).launch {

            val hash = MD5HashKey().getHash(publicKey, privateKey, System.currentTimeMillis())
            val response = NetworkResponse.Success(data = dataObject)
            Mockito.`when`(
                marvelCharacterRemoteDataSource.getMarvelCharacters(
                    publicKey,
                    hash,
                    System.currentTimeMillis()
                )
            ).thenReturn(response)
            val wantedResponse =
                repository.getMarvelCharacters(publicKey, hash, System.currentTimeMillis())
            verify(marvelCharacterRemoteDataSource, times(1)).getMarvelCharacters(
                publicKey,
                hash,
                System.currentTimeMillis()
            )
            assertThat(response == wantedResponse).isTrue()
        }

    }

    @Test
    fun negativeTestGetMarvelCharactersWhenInvalidPublicAndPrivateKey() {
        CoroutineScope(Dispatchers.Default).launch {

            val hash = MD5HashKey().getHash("", "", System.currentTimeMillis())
            val response = NetworkResponse.Error<CharacterListResponse>()
            Mockito.`when`(
                marvelCharacterRemoteDataSource.getMarvelCharacters(
                    "",
                    hash,
                    System.currentTimeMillis()
                )
            ).thenReturn(response)
            val wantedResponse =
                repository.getMarvelCharacters("", hash, System.currentTimeMillis())
            verify(marvelCharacterRemoteDataSource, times(1)).getMarvelCharacters(
                "",
                hash,
                System.currentTimeMillis()
            )
            assertThat(response == wantedResponse).isTrue()
        }

    }

    @Test
    fun negativeTestGetMarvelCharactersWhenInvalidPublicKey() {
        CoroutineScope(Dispatchers.Default).launch {

            val hash = MD5HashKey().getHash("", privateKey, System.currentTimeMillis())
            val response = NetworkResponse.Error<CharacterListResponse>()
            Mockito.`when`(
                marvelCharacterRemoteDataSource.getMarvelCharacters(
                    "",
                    hash,
                    System.currentTimeMillis()
                )
            ).thenReturn(response)
            val wantedResponse =
                repository.getMarvelCharacters("", hash, System.currentTimeMillis())
            verify(marvelCharacterRemoteDataSource, times(1)).getMarvelCharacters(
                "",
                hash,
                System.currentTimeMillis()
            )
            assertThat(response == wantedResponse).isTrue()
        }

    }

    @Test
    fun negativeTestGetMarvelCharactersWhenInvalidPrivateKey() {
        CoroutineScope(Dispatchers.Default).launch {

            val hash = MD5HashKey().getHash(publicKey, "", System.currentTimeMillis())
            val response = NetworkResponse.Error<CharacterListResponse>()
            Mockito.`when`(
                marvelCharacterRemoteDataSource.getMarvelCharacters(
                    publicKey,
                    hash,
                    System.currentTimeMillis()
                )
            ).thenReturn(response)
            val wantedResponse =
                repository.getMarvelCharacters("", hash, System.currentTimeMillis())
            verify(marvelCharacterRemoteDataSource, times(1)).getMarvelCharacters(
                publicKey,
                hash,
                System.currentTimeMillis()
            )
            assertThat(response == wantedResponse).isTrue()
        }

    }


    @Test
    fun positiveTestGetMarvelCharacterDetailsById() {

        CoroutineScope(Dispatchers.Default).launch {
            val characterId = 1017100
            val hash = MD5HashKey().getHash(publicKey, privateKey, System.currentTimeMillis())
            val response = NetworkResponse.Success(data = dataObject)
            Mockito.`when`(
                marvelCharacterRemoteDataSource.getMarvelCharacterByCharacterId(
                    publicKey,
                    hash,
                    System.currentTimeMillis(),
                    characterId
                )
            ).thenReturn(response)
            val wantedResponse = repository.getMarvelCharacterById(
                publicKey,
                hash,
                System.currentTimeMillis(),
                characterId
            )
            verify(marvelCharacterRemoteDataSource, times(1)).getMarvelCharacterByCharacterId(
                publicKey,
                hash,
                System.currentTimeMillis(),
                characterId
            )
            assertThat(response == wantedResponse).isTrue()
        }
    }

    @Test
    fun negativeTestGetMarvelCharacterDetailsByIdWhenAllDataIsInvalid() {

        CoroutineScope(Dispatchers.Default).launch {
            val characterId = 0
            val hash = MD5HashKey().getHash("", "", System.currentTimeMillis())
            val response = NetworkResponse.Error<CharacterListResponse>()
            Mockito.`when`(
                marvelCharacterRemoteDataSource.getMarvelCharacterByCharacterId(
                    "",
                    hash,
                    System.currentTimeMillis(),
                    characterId
                )
            ).thenReturn(response)
            val wantedResponse = repository.getMarvelCharacterById(
                "",
                hash,
                System.currentTimeMillis(),
                characterId
            )
            verify(marvelCharacterRemoteDataSource, times(1)).getMarvelCharacterByCharacterId(
                "",
                hash,
                System.currentTimeMillis(),
                characterId
            )
            assertThat(response == wantedResponse).isTrue()
        }
    }

    @Test
    fun negativeTestGetMarvelCharacterDetailsByIdWhenCharacterIdIsInvalid() {

        CoroutineScope(Dispatchers.Default).launch {
            val characterId = 0
            val hash = MD5HashKey().getHash(publicKey, privateKey, System.currentTimeMillis())
            val response = NetworkResponse.Error<CharacterListResponse>()
            Mockito.`when`(
                marvelCharacterRemoteDataSource.getMarvelCharacterByCharacterId(
                    publicKey,
                    hash,
                    System.currentTimeMillis(),
                    characterId
                )
            ).thenReturn(response)
            val wantedResponse = repository.getMarvelCharacterById(
                publicKey,
                hash,
                System.currentTimeMillis(),
                characterId
            )
            verify(marvelCharacterRemoteDataSource, times(1)).getMarvelCharacterByCharacterId(
                publicKey,
                hash,
                System.currentTimeMillis(),
                characterId
            )
            assertThat(response == wantedResponse).isTrue()
        }
    }

    @Test
    fun negativeTestGetMarvelCharacterDetailsByIdWhenPublicAndPrivateKeyIsInvalid() {

        CoroutineScope(Dispatchers.Default).launch {
            val characterId = 1017100
            val hash = MD5HashKey().getHash("", "", System.currentTimeMillis())
            val response = NetworkResponse.Error<CharacterListResponse>()
            Mockito.`when`(
                marvelCharacterRemoteDataSource.getMarvelCharacterByCharacterId(
                    "",
                    hash,
                    System.currentTimeMillis(),
                    characterId
                )
            ).thenReturn(response)
            val wantedResponse = repository.getMarvelCharacterById(
                "",
                hash,
                System.currentTimeMillis(),
                characterId
            )
            verify(marvelCharacterRemoteDataSource, times(1)).getMarvelCharacterByCharacterId(
                "",
                hash,
                System.currentTimeMillis(),
                characterId
            )
            assertThat(response == wantedResponse).isTrue()
        }
    }

}