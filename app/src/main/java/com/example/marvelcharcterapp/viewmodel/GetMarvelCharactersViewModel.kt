package com.example.marvelcharcterapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.network.NetworkStatus
import com.example.domain.model.DomainMatcherCharacterListResponse
import com.example.domain.usecase.GetMarvelCharactersListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetMarvelCharactersViewModel @Inject constructor(
    private val useCase: GetMarvelCharactersListUseCase
):ViewModel() {

    private val _marvelCharacterList =MutableLiveData<NetworkStatus<DomainMatcherCharacterListResponse>>()
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