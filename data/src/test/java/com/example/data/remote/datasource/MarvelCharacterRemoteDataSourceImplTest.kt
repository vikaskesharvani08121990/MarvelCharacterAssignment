package com.example.data.remote.datasource

import com.example.appcommon.utils.MD5HashKey
import com.example.data.entity.CharacterListResponse
import com.example.data.remote.api.MarvelAPI
import com.example.data.GetRepositoryMockDataFromStringJson
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
    var dataObject: CharacterListResponse? = null

    private val publicKey = "9eda5d952e9fb001de15ad03bcfdd1a8"
    private val privateKey = "76162ae556a83dd9872d502bec095330ddec9ca4"

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
    fun testMarvelCharacters() {
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
    fun testMarvelCharactersById() {

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
}

