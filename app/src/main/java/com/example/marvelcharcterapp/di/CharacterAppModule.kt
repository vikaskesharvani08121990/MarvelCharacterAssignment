package com.example.marvelcharcterapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appcommon.customscopes.FragmentScope
import com.example.appcommon.customscopes.ViewModelKeyScope
import com.example.marvelcharcterapp.factory.ViewModelFactory
import com.example.marvelcharcterapp.viewmodel.GetMarvelCharacterDetailsViewModel
import com.example.marvelcharcterapp.viewmodel.GetMarvelCharactersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
@Module
interface CharacterAppModule {

    @Binds
    @FragmentScope
    fun bindViewModelFactory(factory: ViewModelFactory):ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKeyScope(GetMarvelCharactersViewModel::class)
    fun bindGetMarvelCharactersViewModel(getMarvelCharactersViewModel: GetMarvelCharactersViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKeyScope(GetMarvelCharacterDetailsViewModel::class)
    fun bindGetMarvelCharacterDetailsViewModel(getMarvelCharacterDetailsViewModel: GetMarvelCharacterDetailsViewModel):ViewModel
}