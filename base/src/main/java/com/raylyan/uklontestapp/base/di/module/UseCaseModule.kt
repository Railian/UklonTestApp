package com.raylyan.uklontestapp.base.di.module

import com.raylyan.uklontestapp.business.domain.*
import com.raylyan.uklontestapp.business.logic.usecase.FetchCommentsUseCaseImpl
import com.raylyan.uklontestapp.business.logic.usecase.FetchPostsUseCaseImpl
import com.raylyan.uklontestapp.business.logic.usecase.GetCommentsUseCaseImpl
import com.raylyan.uklontestapp.business.logic.usecase.GetPostsUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetPostsUseCase(
            repository: PostRepository
    ): GetPostsUseCase =
            GetPostsUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideFetchPostsUseCase(
            repository: PostRepository
    ): FetchPostsUseCase =
            FetchPostsUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideGetCommentsUseCase(
            postRepository: PostRepository,
            commentRepository: CommentRepository
    ): GetCommentsUseCase =
            GetCommentsUseCaseImpl(postRepository, commentRepository)

    @Provides
    @Singleton
    fun provideFetchCommentsUseCase(
            repository: CommentRepository
    ): FetchCommentsUseCase =
            FetchCommentsUseCaseImpl(repository)
}