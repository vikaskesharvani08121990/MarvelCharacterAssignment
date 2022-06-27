package com.example.core.di.modules

import android.app.Application
import com.example.core.BaseApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CoreModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application: BaseApplication):Application
}