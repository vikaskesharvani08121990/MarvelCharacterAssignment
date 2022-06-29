package com.example.marvelcharcterapp.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val viewModels: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var viewModel: Provider<out ViewModel>? = viewModels[modelClass]
        if (viewModel == null) for ((key, value) in viewModels) {
            if (modelClass.isAssignableFrom(key)) {
                value.also { viewModel = it }
                break
            }
        }

        requireNotNull(viewModel) { "invalid model class $modelClass" }

        return try {
            viewModel!!.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}