package com.example.approot.di.component

import android.content.Context
import com.example.approot.di.modules.AppRootModule
import com.example.data.di.AppDataModule
import com.example.domain.repository.MarvelCharactersRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppRootModule::class, AppDataModule::class])
interface AppRootComponent {

    val dataRepository: MarvelCharactersRepository

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context):AppRootComponent
    }

}