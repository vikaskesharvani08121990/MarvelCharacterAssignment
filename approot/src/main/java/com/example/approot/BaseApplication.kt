package com.example.approot

import android.app.Application
import android.content.Context
import com.example.appcommon.base.BaseFragment
import com.example.approot.di.component.AppRootComponent
import com.example.approot.di.component.DaggerAppRootComponent

class BaseApplication : Application() {
    private val appRootComponent: AppRootComponent by lazy {
        DaggerAppRootComponent.factory().create(this)
    }

    companion object {
        @JvmStatic
        fun rootComponent(context: Context) =
            (context.applicationContext as BaseApplication).appRootComponent
    }

}

fun BaseFragment.rootComponent() = BaseApplication.rootComponent(requireContext())