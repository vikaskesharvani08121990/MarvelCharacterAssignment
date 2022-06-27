package com.example.domain.usecase


import com.example.common.utils.MD5HashKey
import com.example.common.utils.network.NetworkStatus
import com.example.domain.testUtils.GetDomainFakeData
import com.example.domain.model.DomainMatcherCharacterListResponse
import com.example.domain.repository.GetMarvelCharactersRepository
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

@RunWith(JUnit4::class)
class GetMarvelCharactersListUseCaseTest {

    @Mock
    lateinit var repository: GetMarvelCharactersRepository
    var dataObject: DomainMatcherCharacterListResponse?=null

    @InjectMocks
    lateinit var useCase: GetMarvelCharactersListUseCase

    @Before
    fun setUp() {
        dataObject= GetDomainFakeData.getMarvelCharacterList()

    }

    @After
    fun tearDown() {
        dataObject=null
    }

    @Test
    fun getMarvelCharacters() {
        CoroutineScope(Dispatchers.Default).launch {
            var publicKey="dvksjncjknkjfn"
            var privateKey="dfscndfvkvdfklvmd"
            var hash= MD5HashKey().getHash(publicKey,privateKey,System.currentTimeMillis())
            var response = NetworkStatus.Success<DomainMatcherCharacterListResponse>(data = dataObject)
            Mockito.`when`(repository.getMarvelCharacters(publicKey,hash,System.currentTimeMillis())).
            thenReturn(response)

            var wantedResponse= useCase.invoke(publicKey,privateKey,System.currentTimeMillis())
            verify(repository, times(1)).getMarvelCharacters(publicKey,hash,System.currentTimeMillis())
            Truth.assertThat(wantedResponse.data!=null).isTrue()
            Truth.assertThat(wantedResponse.data!!.charactersList.size==2).isTrue()
        }

    }
}