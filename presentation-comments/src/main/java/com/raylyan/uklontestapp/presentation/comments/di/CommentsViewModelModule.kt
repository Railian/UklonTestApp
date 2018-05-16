package com.raylyan.uklontestapp.presentation.comments.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.raylyan.uklontestapp.base.di.DaggerViewModelFactory
import com.raylyan.uklontestapp.base.di.ViewModelKey
import com.raylyan.uklontestapp.presentation.comments.viewmodel.CommentsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CommentsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CommentsViewModel::class)
    abstract fun bindCommentsViewModel(viewModel: CommentsViewModel): ViewModel

    @Binds
    abstract fun bindPostsViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}