package com.ademozalp.library_service.dto

import com.ademozalp.library_service.model.Library

data class LibraryDto @JvmOverloads constructor(
    val id: String,
    val libraryBooList: List<BookDto>? = ArrayList()
)