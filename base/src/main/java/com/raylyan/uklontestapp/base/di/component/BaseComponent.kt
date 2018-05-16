package com.raylyan.uklontestapp.base.di.component

import com.raylyan.uklontestapp.base.di.module.DataSourceModule
import com.raylyan.uklontestapp.base.di.module.RepositoryModule
import com.raylyan.uklontestapp.base.di.module.UseCaseModule
import com.raylyan.uklontestapp.business.domain.FetchCommentsUseCase
import com.raylyan.uklontestapp.business.domain.FetchPostsUseCase
import com.raylyan.uklontestapp.business.domain.GetCommentsUseCase
import com.raylyan.uklontestapp.business.domain.GetPostsUseCase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DataSourceModule::class,
    RepositoryModule::class,
    UseCaseModule::class
])
interface BaseComponent {
    fun provideGetPostsUseCase(): GetPostsUseCase
    fun provideFetchPostsUseCase(): FetchPostsUseCase
    fun provideGetCommentsUseCase(): GetCommentsUseCase
    fun provideFetchCommentsUseCase(): FetchCommentsUseCase
}