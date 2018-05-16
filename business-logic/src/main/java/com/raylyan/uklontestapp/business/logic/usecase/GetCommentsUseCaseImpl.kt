package com.raylyan.uklontestapp.business.logic.usecase

import com.raylyan.uklontestapp.business.domain.CommentRepository
import com.raylyan.uklontestapp.business.domain.FetchingComments
import com.raylyan.uklontestapp.business.domain.GetCommentsUseCase
import com.raylyan.uklontestapp.business.domain.PostRepository

class GetCommentsUseCaseImpl(
        private val postRepository: PostRepository,
        private val commentRepository: CommentRepository
) : GetCommentsUseCase {

    override fun invoke(postId: Long): FetchingComments {
        return FetchingComments(
                post = postRepository.getPost(postId),
                comments = commentRepository.getComments(postId),
                isFetching = commentRepository.areCommentsFetching(postId)
        )
    }
}