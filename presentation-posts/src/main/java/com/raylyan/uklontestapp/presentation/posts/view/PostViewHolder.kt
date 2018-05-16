package com.raylyan.uklontestapp.presentation.posts.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.raylyan.uklontestapp.business.domain.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(post: Post) {
        itemView.textViewPostTitle.text = post.title
        itemView.textViewPostBody.text = post.body
    }
}