package com.raylyan.uklontestapp.business.logic.usecase

import com.raylyan.uklontestapp.business.domain.FetchPostsUseCase
import com.raylyan.uklontestapp.business.domain.PostRepository
import io.reactivex.Completable

class FetchPostsUseCaseImpl(
        private val repository: PostRepository
) : FetchPostsUseCase {

    override fun invoke(): Completable {
        return repository.fetchPosts()
    }
}