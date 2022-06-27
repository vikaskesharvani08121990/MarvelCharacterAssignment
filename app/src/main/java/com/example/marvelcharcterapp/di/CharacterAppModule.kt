package com.example.marvelcharcterapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.scopes.Fragment
import com.example.common.scopes.ViewModelKey
import com.example.marvelcharcterapp.factory.ViewModelFactory
import com.example.marvelcharcterapp.viewmodel.GetMarvelCharacterDetailsViewModel
import com.example.marvelcharcterapp.viewmodel.GetMarvelCharactersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
@Module
interface CharacterAppModule {

    @Binds
    @Fragment
    fun bindViewModelFactory(factory: ViewModelFactory):ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(GetMarvelCharactersViewModel::class)
    fun bindGetMarvelCharactersViewModel(getMarvelCharactersViewModel: GetMarvelCharactersViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GetMarvelCharacterDetailsViewModel::class)
    fun bindGetMarvelCharacterDetailsViewModel(getMarvelCharacterDetailsViewModel: GetMarvelCharacterDetailsViewModel):ViewModel
}