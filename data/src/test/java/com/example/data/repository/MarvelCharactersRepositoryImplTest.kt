package com.example.data.repository


import com.example.appcommon.utils.MD5HashKey
import com.example.appcommon.utils.NetworkResponse
import com.example.data.entity.CharacterListResponse
import com.example.data.remote.datasource.MarvelCharacterRemoteDataSource
import com.example.data.GetRepositoryMockDataFromStringJson
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
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class MarvelCharactersRepositoryImplTest {

    @Mock
    lateinit var marvelCharacterRemoteDataSource: MarvelCharacterRemoteDataSource

    @InjectMocks
    lateinit var repository: MarvelCharactersRepository

    private var dataObject: CharacterListResponse? = null

    private val publicKey = "dvksjncjknkjfn"
    private val privateKey = "dfscndfvkvdfklvmd"

    @Before
    fun setUp() {
        dataObject = GetRepositoryMockDataFromStringJson.getOneMarvelCharacterList()

    }

    @After
    fun tearDown() {
        dataObject = null
    }

    @Test
    fun getMarvelCharacters() {
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
    fun getMarvelCharactersById() {

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
}