package com.raylyan.uklontestapp.presentation.posts.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.raylyan.uklontestapp.base.di.DaggerViewModelFactory
import com.raylyan.uklontestapp.base.di.ViewModelKey
import com.raylyan.uklontestapp.presentation.posts.viewmodel.PostsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PostsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(viewModel: PostsViewModel): ViewModel

    @Binds
    abstract fun bindPostsViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}