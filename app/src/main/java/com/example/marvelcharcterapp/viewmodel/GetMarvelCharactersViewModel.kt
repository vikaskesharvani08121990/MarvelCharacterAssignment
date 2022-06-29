package com.example.marvelcharcterapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.network.NetworkStatus
import com.example.domain.model.MarvelCharacter
import com.example.domain.usecase.GetMarvelCharactersListUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetMarvelCharactersViewModel @Inject constructor(
    private val useCase: GetMarvelCharactersListUseCaseImpl
) : ViewModel() {

    internal val marvelCharacterList = MutableLiveData<NetworkStatus<List<MarvelCharacter>>>()
    internal fun getMarvelCharacters(
        publicKey: String,
        privateKey: String,
        time: Long
    ) {

        viewModelScope.launch(Dispatchers.IO) {
            val response = useCase.invoke(publicKey, privateKey, time)
            marvelCharacterList.postValue(response)
        }
    }

}