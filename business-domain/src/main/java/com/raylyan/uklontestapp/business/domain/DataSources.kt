package com.raylyan.uklontestapp.business.domain

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface RemoteDataSource {
    fun getPosts(): Single<List<Post>>
    fun getComments(postId: Long): Single<List<Comment>>
}

interface LocalDataSource {
    fun hasPosts(): Single<Boolean>
    fun savePosts(posts: List<Post>): Completable
    fun observePost(id: Long): Observable<Post>
    fun observePosts(): Observable<List<Post>>
    fun saveComments(postId: Long, comments: List<Comment>): Completable
    fun observeComments(postId: Long): Observable<List<Comment>>
}