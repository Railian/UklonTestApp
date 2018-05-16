package com.raylyan.uklontestapp.presentation.comments.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raylyan.uklontestapp.business.domain.Comment
import com.raylyan.uklontestapp.business.domain.FetchCommentsUseCase
import com.raylyan.uklontestapp.business.domain.GetCommentsUseCase
import com.raylyan.uklontestapp.business.domain.Post
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
        private val getCommentsUseCase: GetCommentsUseCase,
        private val fetchCommentsUseCase: FetchCommentsUseCase
) : ViewModel() {

    val post: LiveData<Post> get() = _post
    val comments: LiveData<List<Comment>> get() = _comments
    val isUpdating: LiveData<Boolean> get() = _isUpdating

    private val _post = MutableLiveData<Post>()
    private val _comments = MutableLiveData<List<Comment>>()
    private val _isUpdating = MutableLiveData<Boolean>()

    private val loadDataDisposable = CompositeDisposable()
    private val compositeDisposable = CompositeDisposable()

    fun loadData(postId: Long) {
        loadDataDisposable.clear()
        update(postId)
        getCommentsUseCase(postId).let { (post, comments, isFetching) ->

            post.subscribeOn(Schedulers.io())
                    .subscribe(_post::postValue)
                    .addTo(loadDataDisposable)
                    .addTo(compositeDisposable)

            comments.subscribeOn(Schedulers.io())
                    .subscribe(_comments::postValue)
                    .addTo(loadDataDisposable)
                    .addTo(compositeDisposable)

            isFetching.subscribeOn(Schedulers.io())
                    .subscribe(_isUpdating::postValue)
                    .addTo(loadDataDisposable)
                    .addTo(compositeDisposable)
        }
    }

    fun update(postId: Long) {
        fetchCommentsUseCase(postId)
                .subscribeOn(Schedulers.io())
                .subscribe()
                .addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}