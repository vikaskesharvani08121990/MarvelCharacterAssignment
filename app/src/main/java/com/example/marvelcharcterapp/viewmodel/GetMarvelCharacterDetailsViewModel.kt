package com.example.marvelcharcterapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.network.NetworkStatus
import com.example.domain.model.DomainMatcherCharacterListResponse
import com.example.domain.usecase.GetMarvelCharacterDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetMarvelCharacterDetailsViewModel  @Inject constructor(
    private val useCase: GetMarvelCharacterDetailsUseCase
): ViewModel() {

    private val _marvelCharacterList =
        MutableLiveData<NetworkStatus<DomainMatcherCharacterListResponse>>()
    val marvelCharacterList  =_marvelCharacterList

    fun  getMarvelCharacterDetails(
        publicKey: String,
        privateKey: String,
        time: Long,
        characterId:Int
    ){

        viewModelScope.launch(Dispatchers.IO){
            var response= useCase.invoke(publicKey,privateKey,time,characterId)
            _marvelCharacterList.postValue(response)
        }
    }
}