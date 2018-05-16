package com.raylyan.uklontestapp.base

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.raylyan.uklontestapp.base.di.component.BaseComponent
import com.raylyan.uklontestapp.base.di.component.DaggerBaseComponent

class UklonTestApp : Application() {

    lateinit var baseComponent: BaseComponent private set

    override fun onCreate() {
        super.onCreate()
        baseComponent = DaggerBaseComponent.builder().build()
    }
}

val Activity.uklonTestApp get() = this.application as UklonTestApp
val Fragment.uklonTestApp get() = this.activity?.uklonTestApp