package com.raylyan.uklontestapp.base.di.module

import com.raylyan.uclontestapp.platform.local.cache.CacheAdapter
import com.raylyan.uklontestapp.business.domain.LocalDataSource
import com.raylyan.uklontestapp.business.domain.RemoteDataSource
import com.raylyan.uklontestapp.platform.remote.retrofit.RetrofitAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(): RemoteDataSource =
            RetrofitAdapter.createRemoteDataSource()

    @Provides
    @Singleton
    fun provideLocalDataSource(): LocalDataSource =
            CacheAdapter.createLocalDataSource()
}