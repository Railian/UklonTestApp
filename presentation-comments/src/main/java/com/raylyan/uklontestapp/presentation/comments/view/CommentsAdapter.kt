package com.raylyan.uklontestapp.presentation.comments.view

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.raylyan.uklontestapp.business.domain.Comment
import com.raylyan.uklontestapp.presentation.comments.R
import kotlin.properties.Delegates

class CommentsAdapter: RecyclerView.Adapter<CommentViewHolder>() {

    var comments: List<Comment> by Delegates.observable(
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

    override fun getItemCount(): Int = comments.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }
}

