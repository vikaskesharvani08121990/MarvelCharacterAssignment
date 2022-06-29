package com.example.marvelcharcterapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcommon.utils.NetworkResponse
import com.example.domain.model.MarvelCharacter
import com.example.domain.usecase.GetMarvelCharactersListUseCase
import com.example.marvelcharcterapp.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetMarvelCharactersViewModel @Inject constructor(
    private val useCase: GetMarvelCharactersListUseCase
) : ViewModel() {

    internal val marvelCharacterList = MutableLiveData<NetworkResponse<List<MarvelCharacter>>>()
    init {
        getMarvelCharacters(
            BuildConfig.PUBLIC_KEY,
            BuildConfig.PRIVATE_KEY,
            System.currentTimeMillis()
        )
    }
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