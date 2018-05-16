package com.raylyan.uklontestapp.business.domain

data class Post(
        val id: Long,
        val userId: Long,
        val title: String,
        val body: String
)

data class Comment(
        val id: Long,
        val postId: Long,
        val name: String,
        val email: String,
        val body: String
)