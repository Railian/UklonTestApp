package com.raylyan.uklontestapp.base.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

typealias ViewModelProviders = Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>

@Suppress("UNCHECKED_CAST")
open class DaggerViewModelFactory @Inject constructor(
        private val creators: ViewModelProviders
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("unknown model class " + modelClass)
        }
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    //@Suppress("UNCHECKED_CAST")
    //override fun <T : ViewModel> create(modelClass: Class<T>): T {
    //    val creator = creators[modelClass]
    //            ?: creators.findValue { key, _ -> modelClass.isAssignableFrom(key) }
    //            ?: throw java.lang.IllegalArgumentException("unknown model class " + modelClass)
    //    return creator.get() as T
    //}


}