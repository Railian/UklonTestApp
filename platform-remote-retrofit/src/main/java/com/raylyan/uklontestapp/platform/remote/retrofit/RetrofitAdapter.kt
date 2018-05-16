package com.raylyan.uklontestapp.platform.remote.retrofit

import com.raylyan.uklontestapp.business.domain.Comment
import com.raylyan.uklontestapp.business.domain.Post
import com.raylyan.uklontestapp.business.domain.RemoteDataSource
import com.raylyan.uklontestapp.business.domain.extention.convert
import com.raylyan.uklontestapp.business.domain.extention.mapList
import io.reactivex.Single

object RetrofitAdapter {

    fun createRemoteDataSource(): RemoteDataSource = object : RemoteDataSource {

        private val service: RetrofitRemoteService = RetrofitServiceFactory.create()

        override fun getPosts(): Single<List<Post>> =
                service.getPosts().mapList(::entityAdapter)

        override fun getComments(postId: Long): Single<List<Comment>> =
                service.getsComments(postId).mapList(::entityAdapter)
    }
}

private fun entityAdapter(it: RetrofitPost): Post =
        it.convert { Post(id, userId, title, body) }

private fun entityAdapter(it: RetrofitComment): Comment =
        it.convert { Comment(id, postId, name, email, body) }