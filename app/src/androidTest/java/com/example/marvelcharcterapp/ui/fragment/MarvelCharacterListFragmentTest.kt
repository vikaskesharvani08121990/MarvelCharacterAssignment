package com.example.marvelcharcterapp.ui.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.common.utils.MD5HashKey
import com.example.common.utils.network.NetworkStatus
import com.example.marvelcharcterapp.ui.testUtils.GetFragmentFakeData
import com.example.domain.model.DomainMatcherCharacterListResponse
import com.example.domain.repository.GetMarvelCharactersRepository
import com.example.domain.usecase.GetMarvelCharactersListUseCase
import com.example.marvelcharcterapp.BuildConfig
import com.example.marvelcharcterapp.R
import com.example.marvelcharcterapp.viewmodel.GetMarvelCharactersViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.AdditionalMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito


@RunWith(AndroidJUnit4::class)
class MarvelCharacterListFragmentTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    var dataObject: DomainMatcherCharacterListResponse?=null

    @Mock
    lateinit var  repository: GetMarvelCharactersRepository

    @InjectMocks
    lateinit var useCase: GetMarvelCharactersListUseCase

    @Before
    fun setUp() {
        dataObject= GetFragmentFakeData.getMarvelCharacterList()

    }

    @After
    fun tearDown() {
        dataObject=null
    }

    @Test
    fun testMarvelCharactersUI() {
        CoroutineScope(Dispatchers.Default).launch {
            var publicKey= BuildConfig.PUBLIC_KEY
            var privateKey= BuildConfig.PRIVATE_KEY
            var hash= MD5HashKey().getHash(publicKey,privateKey,System.currentTimeMillis())
            var response = NetworkStatus.Success<DomainMatcherCharacterListResponse>(data = dataObject)
            Mockito.`when`(repository.getMarvelCharacters(publicKey,hash,System.currentTimeMillis())).
            thenReturn(response)


            var viewModel= GetMarvelCharactersViewModel(useCase)
            Espresso.onView(ViewMatchers.withId(R.id.progressBar)).check(
                ViewAssertions.matches(
                    AdditionalMatchers.not(
                        ViewMatchers.isDisplayed()
                    )
                )
            )

            viewModel.getMarvelCharacters(publicKey,privateKey,System.currentTimeMillis())

            Espresso.onView(ViewMatchers.withId(R.id.progressBar)).check(
                ViewAssertions.matches(
                    ViewMatchers.isDisplayed()
                )
            )


        }

    }
}