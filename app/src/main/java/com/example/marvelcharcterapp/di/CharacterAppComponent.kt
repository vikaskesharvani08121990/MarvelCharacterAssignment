package com.example.marvelcharcterapp.di

import com.example.common.scopes.Fragment
import com.example.core.di.component.CoreComponent
import com.example.marvelcharcterapp.ui.fragment.MarvelCharacterDetailsFragment
import com.example.marvelcharcterapp.ui.fragment.MarvelCharacterListFragment
import dagger.Component

@Component(dependencies = [CoreComponent::class], modules = [CharacterAppModule::class])
@Fragment
interface CharacterAppComponent {

    @Component.Factory
    interface Factory{
        fun create(component: CoreComponent):CharacterAppComponent
    }
    fun inject(listFragment: MarvelCharacterListFragment)
    fun inject(detailsFragment: MarvelCharacterDetailsFragment)
}