package com.example.marvelcharcterapp.ui.fragment


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.common.utils.MD5HashKey
import com.example.common.utils.network.NetworkStatus
import com.example.marvelcharcterapp.ui.testUtils.GetFragmentFakeData
import com.example.domain.model.DomainMatcherCharacterListResponse
import com.example.domain.repository.GetMarvelCharactersRepository
import com.example.domain.usecase.GetMarvelCharactersListUseCase
import com.example.marvelcharcterapp.BuildConfig
import com.example.marvelcharcterapp.viewmodel.GetMarvelCharactersViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import com.example.marvelcharcterapp.R
import org.mockito.AdditionalMatchers

@RunWith(AndroidJUnit4::class)
class MarvelCharacterDetailsFragmentTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    var dataObject: DomainMatcherCharacterListResponse? = null

    @Mock
    lateinit var repository: GetMarvelCharactersRepository

    @InjectMocks
    lateinit var useCase: GetMarvelCharactersListUseCase

    @Before
    fun setUp() {
        dataObject = GetFragmentFakeData.getMarvelCharacterDetails()

    }

    @After
    fun tearDown() {
        dataObject = null
    }

    @Test
    fun testMarvelCharacterDetails() {
        CoroutineScope(Dispatchers.Default).launch {
            var publicKey = BuildConfig.PUBLIC_KEY
            var privateKey = BuildConfig.PRIVATE_KEY
            var hash = MD5HashKey().getHash(publicKey, privateKey, System.currentTimeMillis())
            var response =
                NetworkStatus.Success<DomainMatcherCharacterListResponse>(data = dataObject)
            Mockito.`when`(
                repository.getMarvelCharacters(
                    publicKey,
                    hash,
                    System.currentTimeMillis()
                )
            ).thenReturn(response)


            var viewModel = GetMarvelCharactersViewModel(useCase)
            viewModel.getMarvelCharacters(publicKey, privateKey, System.currentTimeMillis())

            onView(withId(R.id.progressBar)).check(
                ViewAssertions.matches(
                    ViewMatchers.isDisplayed()
                )
            )
            verify(viewModel, times(1)).getMarvelCharacters(
                publicKey,
                hash,
                System.currentTimeMillis()
            )
            onView(withId(R.id.progressBar)).check(
                ViewAssertions.matches(
                    AdditionalMatchers.not(
                        ViewMatchers.isDisplayed()
                    )
                )
            )
            onView(withId(R.id.tvCharacterName)).check(
                ViewAssertions.matches(
                    ViewMatchers.isDisplayed()
                )
            )
            onView(withId(R.id.ivCharacter)).check(
                ViewAssertions.matches(
                    ViewMatchers.isDisplayed()
                )
            )
            onView(withId(R.id.tvCharacterId)).check(
                ViewAssertions.matches(
                    ViewMatchers.isDisplayed()
                )
            )
            onView(withId(R.id.tvLabelDescription)).check(
                ViewAssertions.matches(
                    ViewMatchers.isDisplayed()
                )
            )


        }

    }
}