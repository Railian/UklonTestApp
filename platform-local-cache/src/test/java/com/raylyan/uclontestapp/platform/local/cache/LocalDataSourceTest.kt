package com.raylyan.uclontestapp.platform.local.cache

import com.raylyan.uklontestapp.business.domain.Comment
import com.raylyan.uklontestapp.business.domain.Post
import org.junit.Test

class LocalDataSourceTest {

    private val localDataSource = CacheAdapter.createLocalDataSource()

    @Test
    fun testPostsSaving() {
        localDataSource.hasPosts().test().assertValue(false)
        val post = Post(1, 1, "some title", "some body")
        localDataSource.savePosts(listOf(post)).test().assertComplete()
        localDataSource.hasPosts().test().assertValue(true)
        localDataSource.observePosts().test().assertValues(listOf(post))
    }

    @Test
    fun testCommentsSaving() {
        val postId = 1L
        localDataSource.observeComments(postId).test().assertValues(emptyList())
        val comment = Comment(1, 1, "some name", "some email", "some body")
        localDataSource.saveComments(postId, listOf(comment)).test().assertComplete()
        localDataSource.observeComments(postId).test().assertValues(listOf(comment))
    }
}