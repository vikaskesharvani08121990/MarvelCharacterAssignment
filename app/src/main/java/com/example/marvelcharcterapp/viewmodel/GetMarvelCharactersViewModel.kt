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
):ViewModel() {

    private val _marvelCharacterList =MutableLiveData<NetworkStatus<List<MarvelCharacter>>>()
    val marvelCharacterList  =_marvelCharacterList
    fun  getMarvelCharacters(
        publicKey: String,
        privateKey: String,
        time: Long
    ){

        viewModelScope.launch(Dispatchers.IO){
           var response= useCase.invoke(publicKey,privateKey,time)
            _marvelCharacterList.postValue(response)
        }
    }

}