package com.example.marvelcharcterapp.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.utils.MD5HashKey
import com.example.common.utils.network.NetworkStatus
import com.example.domain.usecase.GetMarvelCharactersListUseCaseImpl
import com.example.marvelcharcterapp.BuildConfig
import com.example.marvelcharcterapp.getOrAwaitLiveDataValue
import com.example.marvelcharcterapp.marvelCharacterList
import com.google.common.truth.Truth.assertThat
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
class GetMarvelCharactersViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var useCase: GetMarvelCharactersListUseCaseImpl

    @Test
    fun testMarvelCharacters() {
        CoroutineScope(Dispatchers.Default).launch {
            val publicKey = BuildConfig.PUBLIC_KEY
            val privateKey = BuildConfig.PRIVATE_KEY
            val hash = MD5HashKey().getHash(publicKey, privateKey, System.currentTimeMillis())
            val response = NetworkStatus.Success(data = marvelCharacterList)
            Mockito.`when`(
                useCase.invoke(
                    publicKey,
                    hash,
                    System.currentTimeMillis()
                )
            ).thenReturn(response)

            val viewModel = GetMarvelCharactersViewModel(useCase)
            viewModel.getMarvelCharacters(publicKey, privateKey, System.currentTimeMillis())
            val result = viewModel.marvelCharacterList.getOrAwaitLiveDataValue()

            assertThat(result?.data != null).isTrue()
            verify(viewModel, times(1)).getMarvelCharacters(
                publicKey,
                hash,
                System.currentTimeMillis()
            )
            assertThat(result.data!!.size == 2).isTrue()
        }

    }
}