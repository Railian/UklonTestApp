package com.raylyan.uklontestapp.business.logic.usecase

import com.raylyan.uklontestapp.business.domain.FetchingPosts
import com.raylyan.uklontestapp.business.domain.GetPostsUseCase
import com.raylyan.uklontestapp.business.domain.PostRepository

class GetPostsUseCaseImpl(
        private val repository: PostRepository
) : GetPostsUseCase {

    override fun invoke(): FetchingPosts {
        return FetchingPosts(
                posts = repository.getPosts(),
                isFetching = repository.arePostsFetching()
        )
    }
}