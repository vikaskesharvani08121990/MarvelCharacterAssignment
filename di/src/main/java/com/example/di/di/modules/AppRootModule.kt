package com.example.di.di.modules

import android.app.Application
import com.example.di.BaseApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppRootModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application: BaseApplication): Application

}