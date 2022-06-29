package com.example.marvelcharcterapp.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.utils.MD5HashKey
import com.example.common.utils.network.NetworkStatus
import com.example.domain.usecase.GetMarvelCharacterDetailsUseCaseImpl
import com.example.marvelcharcterapp.BuildConfig
import com.example.marvelcharcterapp.getOrAwaitLiveDataValue
import com.example.marvelcharcterapp.marvelCharacterDetails
import com.google.common.truth.Truth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify


@RunWith(JUnit4::class)
class GetMarvelCharacterDetailsViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    
    @Mock
    lateinit var useCase: GetMarvelCharacterDetailsUseCaseImpl

    @Test
    fun testMarvelCharacterDetails() {
        CoroutineScope(Dispatchers.Default).launch {
            val publicKey = BuildConfig.PUBLIC_KEY
            val privateKey = BuildConfig.PRIVATE_KEY
            val characterId = 1017100
            val hash = MD5HashKey().getHash(publicKey, privateKey, System.currentTimeMillis())
            val response = NetworkStatus.Success(data = marvelCharacterDetails)
            Mockito.`when`(
                useCase.invoke(
                    publicKey,
                    hash,
                    System.currentTimeMillis(),
                    characterId
                )
            ).thenReturn(response)


            val viewModel = GetMarvelCharacterDetailsViewModel(useCase)
            viewModel.getMarvelCharacterDetails(
                publicKey,
                privateKey,
                System.currentTimeMillis(),
                characterId
            )
            val result = viewModel.marvelCharacterDetails.getOrAwaitLiveDataValue()

            Truth.assertThat(result?.data != null).isTrue()
            verify(viewModel, times(1)).getMarvelCharacterDetails(
                publicKey,
                hash,
                System.currentTimeMillis(),
                characterId
            )
            Truth.assertThat(result.data!!.id == 1017100).isTrue()
        }

    }
}