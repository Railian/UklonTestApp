package com.raylyan.uklontestapp.business.domain

import io.reactivex.Completable
import io.reactivex.Observable

interface PostRepository {
    fun getPost(id: Long): Observable<Post>
    fun getPosts(): Observable<List<Post>>
    fun fetchPosts(): Completable
    fun arePostsFetching(): Observable<Boolean>
}

interface CommentRepository {
    fun getComments(postId: Long): Observable<List<Comment>>
    fun fetchComments(postId: Long): Completable
    fun areCommentsFetching(postId: Long): Observable<Boolean>
}