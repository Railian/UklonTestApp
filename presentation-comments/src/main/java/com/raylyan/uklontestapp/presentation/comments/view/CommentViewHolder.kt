package com.raylyan.uklontestapp.presentation.comments.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.raylyan.uklontestapp.business.domain.Comment
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(comment: Comment) {
        itemView.textViewCommentEmail.text = comment.email
        itemView.textViewCommentName.text = comment.name
        itemView.textViewCommentBody.text = comment.body
    }
}