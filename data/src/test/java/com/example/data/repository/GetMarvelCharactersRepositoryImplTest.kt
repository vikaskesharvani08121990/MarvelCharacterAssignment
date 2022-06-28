package com.example.data.repository


import com.example.common.utils.MD5HashKey
import com.example.common.utils.network.NetworkStatus
import com.example.data.entity.CharacterListResponse
import com.example.data.remote.datasource.RemoteDataSource
import com.example.data.testUtils.GetRepositoryFakeDataFromStringJsonFile
import com.example.domain.repository.GetMarvelCharactersRepository
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
class GetMarvelCharactersRepositoryImplTest {

    @Mock
    lateinit var  remoteDataSource: RemoteDataSource

    @InjectMocks
    lateinit var repository: GetMarvelCharactersRepository

    var dataObject: CharacterListResponse?=null

    @Before
    fun setUp() {
        dataObject= GetRepositoryFakeDataFromStringJsonFile.getOneMarvelCharacterList()

    }

    @After
    fun tearDown() {
        dataObject=null
    }

    @Test
    fun getMarvelCharacters() {
        CoroutineScope(Dispatchers.Default).launch {
            var publicKey = "dvksjncjknkjfn"
            var privateKey = "dfscndfvkvdfklvmd"
            var hash= MD5HashKey().getHash(publicKey,privateKey,System.currentTimeMillis())
            var response = NetworkStatus.Success<CharacterListResponse>(data = dataObject)
            Mockito.`when`(remoteDataSource.getMarvelCharacters(publicKey,hash,System.currentTimeMillis())).
            thenReturn(response)
            var wantedResponse= repository.getMarvelCharacters(publicKey,hash,System.currentTimeMillis())
            verify(remoteDataSource, times(1)).getMarvelCharacters(publicKey,hash,System.currentTimeMillis())
            assertThat(response == wantedResponse).isTrue()
        }

    }

    @Test
    fun getMarvelCharactersById() {

        CoroutineScope(Dispatchers.Default).launch {
            var publicKey = "dvksjncjknkjfn"
            var privateKey = "dfscndfvkvdfklvmd"
            var characterId = 1017100
            var hash= MD5HashKey().getHash(publicKey,privateKey,System.currentTimeMillis())
            var response = NetworkStatus.Success<CharacterListResponse>(data = dataObject)
            Mockito.`when`(remoteDataSource.getMarvelCharacterByCharacterId(publicKey,hash,System.currentTimeMillis(),characterId)).
            thenReturn(response)
            var wantedResponse= repository.getMarvelCharacterById(publicKey,hash,System.currentTimeMillis(),characterId)
            verify(remoteDataSource, times(1)).getMarvelCharacterByCharacterId(publicKey,hash,System.currentTimeMillis(),characterId)
            assertThat(response == wantedResponse).isTrue()
        }
    }
}