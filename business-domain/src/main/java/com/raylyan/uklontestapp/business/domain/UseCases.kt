package com.raylyan.uklontestapp.business.domain

import io.reactivex.Completable
import io.reactivex.Observable

data class FetchingPosts(
        val posts: Observable<List<Post>>,
        val isFetching: Observable<Boolean>
)

data class FetchingComments(
        val post: Observable<Post>,
        val comments: Observable<List<Comment>>,
        val isFetching: Observable<Boolean>
)

interface GetPostsUseCase {
    operator fun invoke(): FetchingPosts
}

interface FetchPostsUseCase {
    operator fun invoke(): Completable
}

interface GetCommentsUseCase {
    operator fun invoke(postId: Long): FetchingComments
}

interface FetchCommentsUseCase {
    operator fun invoke(postId: Long): Completable
}