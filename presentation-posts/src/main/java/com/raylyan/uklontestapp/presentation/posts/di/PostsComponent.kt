package com.raylyan.uklontestapp.presentation.posts.di

import com.raylyan.uklontestapp.base.di.Feature
import com.raylyan.uklontestapp.base.di.component.BaseComponent
import com.raylyan.uklontestapp.presentation.posts.view.PostsActivity
import dagger.Component

@Feature
@Component(
        dependencies = [BaseComponent::class],
        modules = [PostsViewModelModule::class]
)
interface PostsComponent {
    fun inject(activity: PostsActivity)
}