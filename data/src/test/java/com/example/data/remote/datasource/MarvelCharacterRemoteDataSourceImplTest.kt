package com.example.data.remote.datasource

import com.example.common.utils.MD5HashKey
import com.example.data.GetRepositoryMockDataFromStringJson
import com.example.data.entity.CharacterListResponse
import com.example.data.remote.api.MarvelAPI
import com.google.common.truth.Truth
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
import retrofit2.Response

@RunWith(JUnit4::class)
class MarvelCharacterRemoteDataSourceImplTest {
    private var dataObject: CharacterListResponse? = null

    private val publicKey = "577ggt5654552e9fb001de15ad"
    private val privateKey = "76ty63g56a83dd9872d502bec"

    @Mock
    lateinit var marvelAPI: MarvelAPI

    @Mock
    lateinit var remoteDataSourceImpl: MarvelCharacterRemoteDataSource

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
            val response = Response.success(dataObject)
            Mockito.`when`(
                marvelAPI.getMarvelCharacters(
                    publicKey,
                    hash,
                    System.currentTimeMillis().toString()
                )
            ).thenReturn(response)
            val wantedResponse = remoteDataSourceImpl.getMarvelCharacters(
                publicKey,
                hash,
                System.currentTimeMillis()
            )
            verify(marvelAPI, times(1)).getMarvelCharacters(
                publicKey,
                hash,
                System.currentTimeMillis().toString()
            )
            Truth.assertThat(response == wantedResponse).isTrue()
        }

    }

    @Test
    fun negativeTestGetMarvelCharactersWhenInvalidPublicAndPrivateKey() {
        CoroutineScope(Dispatchers.Default).launch {
            val hash = MD5HashKey().getHash("", "", System.currentTimeMillis())
            val response = Response.success(CharacterListResponse(code=409))
            Mockito.`when`(
                marvelAPI.getMarvelCharacters(
                    "",
                    hash,
                    System.currentTimeMillis().toString()
                )
            ).thenReturn(response)
            val wantedResponse = remoteDataSourceImpl.getMarvelCharacters(
                "",
                hash,
                System.currentTimeMillis()
            )
            verify(marvelAPI, times(1)).getMarvelCharacters(
                "",
                hash,
                System.currentTimeMillis().toString()
            )
            Truth.assertThat(response == wantedResponse).isTrue()
        }

    }

    @Test
    fun negativeTestGetMarvelCharactersWhenInvalidPublicKey() {
        CoroutineScope(Dispatchers.Default).launch {
            val hash = MD5HashKey().getHash("", privateKey, System.currentTimeMillis())
            val response = Response.success(CharacterListResponse(code=409))
            Mockito.`when`(
                marvelAPI.getMarvelCharacters(
                    "",
                    hash,
                    System.currentTimeMillis().toString()
                )
            ).thenReturn(response)
            val wantedResponse = remoteDataSourceImpl.getMarvelCharacters(
                "",
                hash,
                System.currentTimeMillis()
            )
            verify(marvelAPI, times(1)).getMarvelCharacters(
                "",
                hash,
                System.currentTimeMillis().toString()
            )
            Truth.assertThat(response == wantedResponse).isTrue()
        }

    }

    @Test
    fun negativeTestGetMarvelCharactersWhenInvalidPrivateKey() {
        CoroutineScope(Dispatchers.Default).launch {
            val hash = MD5HashKey().getHash(publicKey, "", System.currentTimeMillis())
            val response = Response.success(CharacterListResponse(code=409))
            Mockito.`when`(
                marvelAPI.getMarvelCharacters(
                    publicKey,
                    hash,
                    System.currentTimeMillis().toString()
                )
            ).thenReturn(response)
            val wantedResponse = remoteDataSourceImpl.getMarvelCharacters(
                publicKey,
                hash,
                System.currentTimeMillis()
            )
            verify(marvelAPI, times(1)).getMarvelCharacters(
                "",
                hash,
                System.currentTimeMillis().toString()
            )
            Truth.assertThat(response == wantedResponse).isTrue()
        }

    }

    @Test
    fun positiveTestMarvelCharactersById() {
        CoroutineScope(Dispatchers.Default).launch {
            val characterId = 1017100
            val hash = MD5HashKey().getHash(publicKey, privateKey, System.currentTimeMillis())
            val response = Response.success(dataObject)
            Mockito.`when`(
                marvelAPI.getMarvelCharacterByCharacterId(
                    characterId,
                    publicKey,
                    hash,
                    System.currentTimeMillis().toString()
                )
            ).thenReturn(response)
            val wantedResponse = remoteDataSourceImpl.getMarvelCharacterByCharacterId(
                publicKey,
                hash,
                System.currentTimeMillis(),
                characterId
            )
            verify(marvelAPI, times(1)).getMarvelCharacterByCharacterId(
                characterId,
                publicKey,
                hash,
                System.currentTimeMillis().toString()
            )
            Truth.assertThat(response == wantedResponse).isTrue()
        }
    }


    @Test
    fun negativeTestMarvelCharactersByIdWhenPublicAndPrivateKeyIsInvalid() {
        CoroutineScope(Dispatchers.Default).launch {
            val characterId = 1017100
            val hash = MD5HashKey().getHash("", "", System.currentTimeMillis())
            val response = Response.success(CharacterListResponse(code=409))
            Mockito.`when`(
                marvelAPI.getMarvelCharacterByCharacterId(
                    characterId,
                    "",
                    hash,
                    System.currentTimeMillis().toString()
                )
            ).thenReturn(response)
            val wantedResponse = remoteDataSourceImpl.getMarvelCharacterByCharacterId(
                "",
                hash,
                System.currentTimeMillis(),
                characterId
            )
            verify(marvelAPI, times(1)).getMarvelCharacterByCharacterId(
                characterId,
                "",
                hash,
                System.currentTimeMillis().toString()
            )
            Truth.assertThat(response == wantedResponse).isTrue()
        }
    }

    @Test
    fun negativeTestMarvelCharactersByIdWhenCharacterIdInvalid() {
        CoroutineScope(Dispatchers.Default).launch {
            val characterId = 0
            val hash = MD5HashKey().getHash(publicKey, privateKey, System.currentTimeMillis())
            val response = Response.success(CharacterListResponse(code=409))
            Mockito.`when`(
                marvelAPI.getMarvelCharacterByCharacterId(
                    characterId,
                    publicKey,
                    hash,
                    System.currentTimeMillis().toString()
                )
            ).thenReturn(response)
            val wantedResponse = remoteDataSourceImpl.getMarvelCharacterByCharacterId(
                publicKey,
                hash,
                System.currentTimeMillis(),
                characterId
            )
            verify(marvelAPI, times(1)).getMarvelCharacterByCharacterId(
                characterId,
                publicKey,
                hash,
                System.currentTimeMillis().toString()
            )
            Truth.assertThat(response == wantedResponse).isTrue()
        }
    }

    @Test
    fun negativeTestMarvelCharactersByIdWhenAllParameterAreInvalid() {
        CoroutineScope(Dispatchers.Default).launch {
            val characterId = 0
            val hash = MD5HashKey().getHash("", "", System.currentTimeMillis())
            val response = Response.success(CharacterListResponse(code=409))
            Mockito.`when`(
                marvelAPI.getMarvelCharacterByCharacterId(
                    characterId,
                    "",
                    hash,
                    System.currentTimeMillis().toString()
                )
            ).thenReturn(response)
            val wantedResponse = remoteDataSourceImpl.getMarvelCharacterByCharacterId(
                "",
                hash,
                System.currentTimeMillis(),
                characterId
            )
            verify(marvelAPI, times(1)).getMarvelCharacterByCharacterId(
                characterId,
                "",
                hash,
                System.currentTimeMillis().toString()
            )
            Truth.assertThat(response == wantedResponse).isTrue()
        }
    }


}

