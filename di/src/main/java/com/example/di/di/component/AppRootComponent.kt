package com.example.di.di.component

import android.content.Context
import com.example.di.di.modules.AppRootModule
import com.example.data.di.AppDataModule
import com.example.domain.repository.MarvelCharactersRepository
import com.example.domain.usecase.GetMarvelCharacterDetailsUseCase
import com.example.domain.usecase.GetMarvelCharactersListUseCase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppRootModule::class, AppDataModule::class])
interface AppRootComponent {

    val dataRepository: MarvelCharactersRepository

    val listUseCase: GetMarvelCharactersListUseCase

    val detailsUseCase: GetMarvelCharacterDetailsUseCase

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppRootComponent
    }

}