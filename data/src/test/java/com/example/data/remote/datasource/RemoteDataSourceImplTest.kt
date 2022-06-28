package com.example.data.remote.datasource

import com.example.common.utils.MD5HashKey
import com.example.data.entity.CharacterListResponse
import com.example.data.remote.api.MarvelAPI
import com.example.data.testUtils.GetRepositoryFakeDataFromStringJsonFile
import com.google.common.truth.Truth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import retrofit2.Response

@RunWith(JUnit4::class)
class RemoteDataSourceImplTest {
    var dataObject: CharacterListResponse? = null

    @Mock
    lateinit var marvelAPI: MarvelAPI

    @InjectMocks
    lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

    @Before
    fun setUp() {
        dataObject = GetRepositoryFakeDataFromStringJsonFile.getOneMarvelCharacterList()

    }

    @After
    fun tearDown() {
        dataObject = null
    }

    @Test
    fun testMarvelCharacters() {
        CoroutineScope(Dispatchers.Default).launch {
            val publicKey = "dvksjncjknkjfn"
            val privateKey = "dfscndfvkvdfklvmd"
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
            val publicKey = "dvksjncjknkjfn"
            val privateKey = "dfscndfvkvdfklvmd"
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

