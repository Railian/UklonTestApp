package com.raylyan.uklontestapp.platform.remote.retrofit

import com.google.gson.annotations.SerializedName

internal data class RetrofitPost(
        @SerializedName("id") val id: Long,
        @SerializedName("userId") val userId: Long,
        @SerializedName("title") val title: String,
        @SerializedName("body") val body: String
)

internal data class RetrofitComment(
        @SerializedName("id") val id: Long,
        @SerializedName("postId") val postId: Long,
        @SerializedName("name") val name: String,
        @SerializedName("email") val email: String,
        @SerializedName("body") val body: String
)