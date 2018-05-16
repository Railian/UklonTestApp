package com.raylyan.uklontestapp.base

import android.content.Intent
import android.net.Uri

object AppNavigation {

    private fun baseNavigationUri(): Uri.Builder = Uri.Builder()
            .scheme("https")
            .authority("uklontestapp.raylyan.com")

    object Posts {

        val uri: Uri = baseNavigationUri()
                .appendPath("posts")
                .build()
    }

    class Comments(postId: Long) {

        @Suppress("ClassName")
        sealed class Parameter(val key: String) {
            object POST_ID : Parameter("postId")
        }

        companion object {
            fun extract(parameter: Parameter, uri: Uri): String {
                return uri.getQueryParameter(parameter.key)
            }
        }

        val uri: Uri = baseNavigationUri()
                .appendPath("comments")
                .appendQueryParameter(Parameter.POST_ID.key, postId.toString())
                .build()
    }
}

fun Uri.createDeepLinkIntent(applicationPackageName: String): Intent {
    return Intent().also {
        it.data = this
        it.action = Intent.ACTION_VIEW
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        it.addCategory(Intent.CATEGORY_BROWSABLE)
        it.`package` = applicationPackageName
    }
}