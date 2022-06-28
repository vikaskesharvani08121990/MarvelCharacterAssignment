package com.example.marvelcharcterapp.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.utils.MD5HashKey
import com.example.common.utils.network.NetworkStatus
import com.example.domain.model.CharacterDetails
import com.example.marvelcharcterapp.testUtils.GetViewModelFakeData
import com.example.domain.repository.GetMarvelCharactersRepository
import com.example.domain.usecase.GetMarvelCharacterDetailsUseCaseImpl
import com.example.marvelcharcterapp.BuildConfig
import com.example.marvelcharcterapp.testUtils.getOrAwaitValue
import com.google.common.truth.Truth
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
class GetMarvelCharacterDetailsViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    var dataObject: CharacterDetails?=null

    @Mock
    lateinit var  repository: GetMarvelCharactersRepository

    @InjectMocks
    lateinit var useCase: GetMarvelCharacterDetailsUseCaseImpl

    @Before
    fun setUp() {
        dataObject= GetViewModelFakeData.getMarvelCharacterDetails()

    }

    @After
    fun tearDown() {
        dataObject=null
    }

    @Test
    fun testMarvelCharacterDetails() {
        CoroutineScope(Dispatchers.Default).launch {
            var publicKey= BuildConfig.PUBLIC_KEY
            var privateKey= BuildConfig.PRIVATE_KEY
            var characterId=1017100
            var hash= MD5HashKey().getHash(publicKey,privateKey,System.currentTimeMillis())
            var response = NetworkStatus.Success<CharacterDetails>(data = dataObject)
            Mockito.`when`(repository.getMarvelCharacterById(publicKey,hash,System.currentTimeMillis(),characterId)).
            thenReturn(response)


            var viewModel=GetMarvelCharacterDetailsViewModel(useCase)
            viewModel.getMarvelCharacterDetails(publicKey,privateKey,System.currentTimeMillis(),characterId)
            var result=viewModel.marvelCharacterDetails.getOrAwaitValue()

            Truth.assertThat(result?.data != null).isTrue()
            verify(viewModel, times(1)).getMarvelCharacterDetails(publicKey,hash,System.currentTimeMillis(),characterId)
            Truth.assertThat(result.data!!.characterDetails!=null).isTrue()
        }

    }
}