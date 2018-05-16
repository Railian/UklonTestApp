package com.raylyan.uklontestapp.base.di.module

import com.raylyan.uklontestapp.business.domain.CommentRepository
import com.raylyan.uklontestapp.business.domain.LocalDataSource
import com.raylyan.uklontestapp.business.domain.PostRepository
import com.raylyan.uklontestapp.business.domain.RemoteDataSource
import com.raylyan.uklontestapp.business.logic.repository.CommentRepositoryImpl
import com.raylyan.uklontestapp.business.logic.repository.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providePostRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
    ): PostRepository =
            PostRepositoryImpl(remoteDataSource, localDataSource)

    @Provides
    @Singleton
    fun provideCommentRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
    ): CommentRepository =
            CommentRepositoryImpl(remoteDataSource, localDataSource)
}