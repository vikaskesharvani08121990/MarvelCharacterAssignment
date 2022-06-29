package com.example.marvelcharcterapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcommon.utils.NetworkResponse
import com.example.domain.model.MarvelCharacter
import com.example.domain.usecase.GetMarvelCharacterDetailsUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetMarvelCharacterDetailsViewModel @Inject constructor(
    private val useCase: GetMarvelCharacterDetailsUseCaseImpl
) : ViewModel() {

    internal val marvelCharacterDetails =
        MutableLiveData<NetworkResponse<MarvelCharacter>>()

    internal fun getMarvelCharacterDetails(
        publicKey: String,
        privateKey: String,
        time: Long,
        characterId: Int
    ) {

        viewModelScope.launch(Dispatchers.IO) {
            val response = useCase.invoke(publicKey, privateKey, time, characterId)
            marvelCharacterDetails.postValue(response)
        }
    }
}