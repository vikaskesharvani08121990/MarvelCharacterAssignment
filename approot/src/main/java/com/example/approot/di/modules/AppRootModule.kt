package com.example.approot.di.modules

import android.app.Application
import com.example.approot.BaseApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppRootModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application: BaseApplication):Application
}