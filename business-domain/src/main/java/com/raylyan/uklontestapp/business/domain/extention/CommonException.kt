package com.raylyan.uklontestapp.business.domain.extention

fun <T, R> T.convert(converter: T.() -> R): R =
        converter.invoke(this)