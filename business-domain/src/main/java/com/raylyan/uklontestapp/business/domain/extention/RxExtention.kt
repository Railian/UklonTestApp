package com.raylyan.uklontestapp.business.domain.extention

import io.reactivex.Single

fun <T, R> Single<List<T>>.mapList(mapper: (T) -> R): Single<List<R>> =
        this.map { it.map(mapper) }