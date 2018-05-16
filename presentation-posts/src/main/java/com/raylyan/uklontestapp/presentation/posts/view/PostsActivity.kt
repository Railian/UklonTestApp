package com.raylyan.uklontestapp.presentation.posts.view

import android.app.ActivityOptions
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.raylyan.uklontestapp.base.AppNavigation
import com.raylyan.uklontestapp.base.createDeepLinkIntent
import com.raylyan.uklontestapp.base.uklonTestApp
import com.raylyan.uklontestapp.presentation.posts.R
import com.raylyan.uklontestapp.presentation.posts.di.DaggerPostsComponent
import com.raylyan.uklontestapp.presentation.posts.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.activity_posts.*
import javax.inject.Inject


class PostsActivity : AppCompatActivity() {

    private lateinit var postsAdapter: PostsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PostsViewModel

    private fun injectDependencies() {
        DaggerPostsComponent.builder()
                .baseComponent(uklonTestApp.baseComponent)
                .build()
                .inject(this)
    }

    private fun initView() {
        postsAdapter = PostsAdapter(onPostClicked = { post, view ->
            val options = ActivityOptions.makeSceneTransitionAnimation(this, view, "root")
            val commentsIntent = AppNavigation.Comments(post.id).uri.createDeepLinkIntent(application.packageName)
            startActivity(commentsIntent, options.toBundle())

        })
        with(recyclerView) {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(this@PostsActivity)
            addItemDecoration(DividerItemDecoration(this@PostsActivity, LinearLayoutManager.VERTICAL))
        }
        swipeRefreshLayout.setOnRefreshListener(viewModel::update)

        viewModel.posts.observe(this, Observer { posts ->
            postsAdapter.posts = posts ?: emptyList()
        })
        viewModel.isUpdating.observe(this, Observer { isUpdating ->
            swipeRefreshLayout.isRefreshing = isUpdating ?: false
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        injectDependencies()
        viewModel = ViewModelProviders.of(this, viewModelFactory)[PostsViewModel::class.java]
        initView()
    }
}
