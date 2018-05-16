package com.raylyan.uklontestapp.presentation.comments.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.raylyan.uklontestapp.base.AppNavigation
import com.raylyan.uklontestapp.base.AppNavigation.Comments.Parameter
import com.raylyan.uklontestapp.base.uklonTestApp
import com.raylyan.uklontestapp.presentation.comments.R
import com.raylyan.uklontestapp.presentation.comments.di.DaggerCommentsComponent
import com.raylyan.uklontestapp.presentation.comments.viewmodel.CommentsViewModel
import kotlinx.android.synthetic.main.activity_comments.*
import javax.inject.Inject

class CommentsActivity : AppCompatActivity() {

    private val postId: Long by lazy { AppNavigation.Comments.extract(Parameter.POST_ID, intent.data).toLong() }

    private lateinit var commentsAdapter: CommentsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CommentsViewModel

    private fun injectDependencies() {
        DaggerCommentsComponent.builder()
                .baseComponent(uklonTestApp.baseComponent)
                .build()
                .inject(this)
    }

    private fun initView() {
        commentsAdapter = CommentsAdapter()
        with(recyclerView) {
            adapter = commentsAdapter
            layoutManager = LinearLayoutManager(this@CommentsActivity)
        }
        swipeRefreshLayout.setOnRefreshListener { viewModel.update(postId) }

        viewModel.post.observe(this, Observer { post ->
            textViewPostTitle.text = post?.title
            textViewPostBody.text = post?.body
        })
        viewModel.comments.observe(this, Observer { comments ->
            commentsAdapter.comments = comments ?: emptyList()
        })
        viewModel.isUpdating.observe(this, Observer { isUpdating ->
            swipeRefreshLayout.isRefreshing = isUpdating ?: false
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        injectDependencies()
        viewModel = ViewModelProviders.of(this, viewModelFactory)[CommentsViewModel::class.java]
        savedInstanceState ?: viewModel.loadData(postId)
        initView()
    }
}
