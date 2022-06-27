package com.example.marvelcharcterapp.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.utils.MD5HashKey
import com.example.common.utils.network.NetworkStatus
import com.example.marvelcharcterapp.testUtils.GetViewModelFakeData
import com.example.domain.model.DomainMatcherCharacterListResponse
import com.example.domain.repository.GetMarvelCharactersRepository
import com.example.domain.usecase.GetMarvelCharactersListUseCase
import com.example.marvelcharcterapp.BuildConfig
import com.example.marvelcharcterapp.testUtils.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class GetMarvelCharactersViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
      var dataObject: DomainMatcherCharacterListResponse?=null

    @Mock
   lateinit var  repository: GetMarvelCharactersRepository

   @InjectMocks
   lateinit var useCase: GetMarvelCharactersListUseCase

    @Before
    fun setUp() {
        dataObject= GetViewModelFakeData.getMarvelCharacterList()

    }

    @After
    fun tearDown() {
        dataObject=null
    }

    @Test
    fun testMarvelCharacters() {
        CoroutineScope(Dispatchers.Default).launch {
            var publicKey= BuildConfig.PUBLIC_KEY
            var privateKey=BuildConfig.PRIVATE_KEY
            var hash= MD5HashKey().getHash(publicKey,privateKey,System.currentTimeMillis())
            var response = NetworkStatus.Success<DomainMatcherCharacterListResponse>(data = dataObject)
            Mockito.`when`(repository.getMarvelCharacters(publicKey,hash,System.currentTimeMillis())).
            thenReturn(response)


            var viewModel=GetMarvelCharactersViewModel(useCase)
            viewModel.getMarvelCharacters(publicKey,privateKey,System.currentTimeMillis())
            var result=viewModel.marvelCharacterList.getOrAwaitValue()

            assertThat(result?.data != null).isTrue()
            verify(viewModel, times(1)).getMarvelCharacters(publicKey,hash,System.currentTimeMillis())
            assertThat(result.data!!.charactersList.size==2).isTrue()
        }

    }
}