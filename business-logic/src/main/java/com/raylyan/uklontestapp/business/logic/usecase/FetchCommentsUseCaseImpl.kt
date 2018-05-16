package com.raylyan.uklontestapp.business.logic.usecase

import com.raylyan.uklontestapp.business.domain.CommentRepository
import com.raylyan.uklontestapp.business.domain.FetchCommentsUseCase
import io.reactivex.Completable

class FetchCommentsUseCaseImpl(
        private val repository: CommentRepository
) : FetchCommentsUseCase {

    override fun invoke(postId: Long): Completable {
        return repository.fetchComments(postId)
    }
}