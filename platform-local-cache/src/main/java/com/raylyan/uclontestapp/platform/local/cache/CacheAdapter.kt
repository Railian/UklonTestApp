package com.raylyan.uclontestapp.platform.local.cache

import com.raylyan.uklontestapp.business.domain.LocalDataSource

object CacheAdapter {

    fun createLocalDataSource(): LocalDataSource =
            CacheLocalDataSource()
}