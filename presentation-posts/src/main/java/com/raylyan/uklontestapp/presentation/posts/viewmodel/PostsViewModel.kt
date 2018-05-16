package com.raylyan.uklontestapp.presentation.posts.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raylyan.uklontestapp.business.domain.FetchPostsUseCase
import com.raylyan.uklontestapp.business.domain.GetPostsUseCase
import com.raylyan.uklontestapp.business.domain.Post
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor(
        getPostsUseCase: GetPostsUseCase,
        private val fetchPostsUseCase: FetchPostsUseCase
) : ViewModel() {

    val posts: LiveData<List<Post>> get() = _posts
    val isUpdating: LiveData<Boolean> get() = _isUpdating

    private val _posts = MutableLiveData<List<Post>>()
    private val _isUpdating = MutableLiveData<Boolean>()

    private val compositeDisposable = CompositeDisposable()

    init {
        getPostsUseCase().let { (posts, isFetching) ->

            posts.subscribeOn(Schedulers.io())
                    .subscribe(_posts::postValue)
                    .addTo(compositeDisposable)

            isFetching.subscribeOn(Schedulers.io())
                    .subscribe(_isUpdating::postValue)
                    .addTo(compositeDisposable)
        }
    }

    fun update() {
        fetchPostsUseCase()
                .subscribeOn(Schedulers.io())
                .subscribe()
                .addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}