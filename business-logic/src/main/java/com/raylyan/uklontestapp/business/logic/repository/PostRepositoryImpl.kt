package com.raylyan.uklontestapp.business.logic.repository

import com.raylyan.uklontestapp.business.domain.LocalDataSource
import com.raylyan.uklontestapp.business.domain.Post
import com.raylyan.uklontestapp.business.domain.PostRepository
import com.raylyan.uklontestapp.business.domain.RemoteDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class PostRepositoryImpl(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource
) : PostRepository {

    private val arePostsFetchingSubject = BehaviorSubject.createDefault(false)

    override fun getPost(id: Long): Observable<Post> {
        return localDataSource.observePost(id)
                .mergeWith(fetchPostsIfNotExist())
    }

    override fun getPosts(): Observable<List<Post>> {
        return localDataSource.observePosts()
                .mergeWith(fetchPostsIfNotExist())
    }

    override fun fetchPosts(): Completable {
        return remoteDataSource.getPosts()
                .flatMapCompletable { localDataSource.savePosts(it) }
                .doOnSubscribe { arePostsFetchingSubject.onNext(true) }
                .doFinally { arePostsFetchingSubject.onNext(false) }
    }

    override fun arePostsFetching(): Observable<Boolean> {
        return arePostsFetchingSubject
    }

    private fun fetchPostsIfNotExist(): Completable {
        return localDataSource.hasPosts()
                .flatMapCompletable { hasSavedPosts ->
                    if (!hasSavedPosts) fetchPosts()
                    else Completable.complete()
                }
    }
}
