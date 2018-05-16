package com.raylyan.uclontestapp.platform.local.cache

import android.util.Log
import com.raylyan.uklontestapp.business.domain.Comment
import com.raylyan.uklontestapp.business.domain.LocalDataSource
import com.raylyan.uklontestapp.business.domain.Post
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

@Suppress("JoinDeclarationAndAssignment")
class CacheLocalDataSource : LocalDataSource {

    private val postsSubject: BehaviorSubject<List<Post>>
    private val commentsSubject: BehaviorSubject<Map<Long, List<Comment>>>

    init {
        postsSubject = BehaviorSubject.create()
        commentsSubject = BehaviorSubject.createDefault(mapOf())
    }

    override fun hasPosts(): Single<Boolean> {
        return Single.just(postsSubject.value != null)
    }

    override fun savePosts(posts: List<Post>): Completable {
        return Completable.fromCallable { postsSubject.onNext(posts) }
    }

    override fun observePost(id: Long): Observable<Post> {
        return postsSubject
                .filter { it.find { it.id == id } != null }
                .distinctUntilChanged()
                .map { it.find { it.id == id } }
    }

    override fun observePosts(): Observable<List<Post>> {
        return postsSubject.distinctUntilChanged()
    }

    override fun saveComments(postId: Long, comments: List<Comment>): Completable {
        comments.find { it.postId != postId }?.let {
            val warningMessage = """saveComments:
                | You are trying to save comments with difference postId. Only comments with
                | postId $postId will be saved to cash. Other comments will be ignored.
            """.trimMargin()
            Log.w("CashStorageImpl", warningMessage)
        }
        val commentsMap = commentsSubject.value!!.toMutableMap()
        commentsMap[postId] = comments.filter { it.postId == postId }
        return Completable.fromCallable { commentsSubject.onNext(commentsMap) }
    }

    override fun observeComments(postId: Long): Observable<List<Comment>> {
        return commentsSubject.map { it[postId] ?: emptyList() }.distinctUntilChanged()
    }
}