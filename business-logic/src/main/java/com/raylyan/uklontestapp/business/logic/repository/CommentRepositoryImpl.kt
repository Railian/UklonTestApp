package com.raylyan.uklontestapp.business.logic.repository

import com.raylyan.uklontestapp.business.domain.Comment
import com.raylyan.uklontestapp.business.domain.CommentRepository
import com.raylyan.uklontestapp.business.domain.LocalDataSource
import com.raylyan.uklontestapp.business.domain.RemoteDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class CommentRepositoryImpl(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource
) : CommentRepository {

    private val areCommentsFetchingSubject = mutableMapOf<Long, BehaviorSubject<Boolean>>()

    override fun getComments(postId: Long): Observable<List<Comment>> {
        return localDataSource.observeComments(postId)
    }

    override fun fetchComments(postId: Long): Completable {
        return remoteDataSource.getComments(postId)
                .flatMapCompletable { localDataSource.saveComments(postId, it) }
                .doOnSubscribe { areCommentsFetchingSubject(postId).onNext(true) }
                .doFinally { areCommentsFetchingSubject(postId).onNext(false) }
    }

    override fun areCommentsFetching(postId: Long): Observable<Boolean> {
        return areCommentsFetchingSubject(postId)
    }

    @Synchronized
    private fun areCommentsFetchingSubject(postId: Long): BehaviorSubject<Boolean> {
        val areCommentsFetching = areCommentsFetchingSubject[postId]
                ?: BehaviorSubject.createDefault(false)
        if (areCommentsFetchingSubject[postId] == null)
            areCommentsFetchingSubject[postId] = areCommentsFetching
        return areCommentsFetching
    }
}