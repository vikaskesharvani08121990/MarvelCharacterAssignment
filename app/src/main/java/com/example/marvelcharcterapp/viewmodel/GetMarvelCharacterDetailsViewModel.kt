package com.example.marvelcharcterapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.network.NetworkStatus
import com.example.domain.model.MarvelCharacter
import com.example.domain.usecase.GetMarvelCharacterDetailsUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetMarvelCharacterDetailsViewModel  @Inject constructor(
    private val useCase: GetMarvelCharacterDetailsUseCaseImpl
): ViewModel() {

    private val _marvelCharacterDetails =
        MutableLiveData<NetworkStatus<MarvelCharacter>>()
    val marvelCharacterDetails  =_marvelCharacterDetails

    fun  getMarvelCharacterDetails(
        publicKey: String,
        privateKey: String,
        time: Long,
        characterId:Int
    ){

        viewModelScope.launch(Dispatchers.IO){
            var response= useCase.invoke(publicKey,privateKey,time,characterId)
            _marvelCharacterDetails.postValue(response)
        }
    }
}