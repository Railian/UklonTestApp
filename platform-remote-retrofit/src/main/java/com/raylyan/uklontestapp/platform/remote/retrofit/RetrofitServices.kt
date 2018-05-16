package com.raylyan.uklontestapp.platform.remote.retrofit

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

internal object RetrofitServiceFactory {

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    internal inline fun <reified T> create(): T = retrofit.create(T::class.java)
}

internal interface RetrofitRemoteService {

    @GET("/posts")
    fun getPosts(): Single<List<RetrofitPost>>

    @GET("/comments")
    fun getsComments(@Query("postId") postId: Long): Single<List<RetrofitComment>>
}