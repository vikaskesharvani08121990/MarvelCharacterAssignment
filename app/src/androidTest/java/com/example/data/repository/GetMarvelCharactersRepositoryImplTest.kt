package com.example.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.common.utils.MD5HashKey
import com.example.common.utils.network.NetworkStatus
import com.example.data.testUtils.GetRepositoryFakeDataFromStringJsonFile
import com.example.data.entity.DataCharacterListDTO
import com.example.data.remote.datasource.RemoteDataSource
import com.example.data.remote.datasource.RemoteDataSourceImpl
import com.example.marvelcharcterapp.BuildConfig
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(AndroidJUnit4::class)
class GetMarvelCharactersRepositoryImplTest {
    @Mock
     lateinit var  remoteDataSource: RemoteDataSource

     @InjectMocks
     lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

      var dataObject: DataCharacterListDTO?=null

    @Before
    fun setUp() {
        dataObject=GetRepositoryFakeDataFromStringJsonFile.getOneMarvelCharacterList()

    }

    @After
    fun tearDown() {
        dataObject=null
    }

    @Test
    fun getMarvelCharacters() {
        CoroutineScope(Dispatchers.Default).launch {
            var hash=MD5HashKey().getHash(BuildConfig.PUBLIC_KEY,BuildConfig.PRIVATE_KEY,System.currentTimeMillis())
            var response =NetworkStatus.Success<DataCharacterListDTO>(data = dataObject)
            Mockito.`when`(remoteDataSource.getMarvelCharacters(BuildConfig.PUBLIC_KEY,hash,System.currentTimeMillis())).
            thenReturn(response)
           var wantedResponse= remoteDataSourceImpl.getMarvelCharacters(BuildConfig.PUBLIC_KEY,hash,System.currentTimeMillis())
            verify(remoteDataSource, times(1)).getMarvelCharacters(BuildConfig.PUBLIC_KEY,hash,System.currentTimeMillis())
            assertThat(response == wantedResponse).isTrue()
        }

    }

    @Test
    fun getMarvelCharactersById() {
    }
}