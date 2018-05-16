package com.raylyan.uklontestapp.presentation.posts.view

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raylyan.uklontestapp.business.domain.Post
import com.raylyan.uklontestapp.presentation.posts.R
import kotlin.properties.Delegates

class PostsAdapter(
        private val onPostClicked: (post: Post, view: View) -> Unit
) : RecyclerView.Adapter<PostViewHolder>() {

    var posts: List<Post> by Delegates.observable(
            initialValue = emptyList(),
            onChange = { _, oldItems, newItems ->
                DiffUtil.calculateDiff(object : DiffUtil.Callback() {

                    override fun getOldListSize(): Int = oldItems.size
                    override fun getNewListSize(): Int = newItems.size

                    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                            oldItems[oldItemPosition].id == newItems[newItemPosition].id

                    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                            oldItems[oldItemPosition] == newItems[newItemPosition]

                }, true).dispatchUpdatesTo(this)
            }
    )

    override fun getItemCount(): Int = posts.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_post, parent, false)
        val holder = PostViewHolder(itemView)
        itemView.setOnClickListener { onPostClicked(posts[holder.adapterPosition], it) }
        return holder
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }
}

