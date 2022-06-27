package com.example.core

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.core.di.component.CoreComponent
import com.example.core.di.component.DaggerCoreComponent

class BaseApplication:Application() {
  private val coreComponent:CoreComponent by lazy {
   DaggerCoreComponent.factory().create(this)
  }

  companion object{
    @JvmStatic
   fun coreComponent(context: Context)=(context.applicationContext as BaseApplication).coreComponent
  }


}
fun Activity.coreComponent()=BaseApplication.coreComponent(this)
fun Fragment.coreComponent()=BaseApplication.coreComponent(requireContext())