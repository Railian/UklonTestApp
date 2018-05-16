package com.raylyan.uklontestapp.presentation.comments.di

import com.raylyan.uklontestapp.base.di.Feature
import com.raylyan.uklontestapp.base.di.component.BaseComponent
import com.raylyan.uklontestapp.presentation.comments.view.CommentsActivity
import dagger.Component

@Feature
@Component(
        dependencies = [BaseComponent::class],
        modules = [CommentsViewModelModule::class]
)
interface CommentsComponent {
    fun inject(activity: CommentsActivity)
}