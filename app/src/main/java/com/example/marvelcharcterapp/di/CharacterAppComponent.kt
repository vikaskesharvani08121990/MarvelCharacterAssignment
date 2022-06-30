package com.example.marvelcharcterapp.di

import com.example.common.customscopes.FragmentScope
import com.example.di.di.component.AppRootComponent
import com.example.marvelcharcterapp.ui.fragment.MarvelCharacterDetailsFragment
import com.example.marvelcharcterapp.ui.fragment.MarvelCharacterListFragment
import dagger.Component

@Component(dependencies = [AppRootComponent::class], modules = [CharacterAppModule::class])
@FragmentScope
interface CharacterAppComponent {

    @Component.Factory
    interface Factory{
        fun create(component: AppRootComponent):CharacterAppComponent
    }
    fun inject(listFragment: MarvelCharacterListFragment)
    fun inject(detailsFragment: MarvelCharacterDetailsFragment)
}