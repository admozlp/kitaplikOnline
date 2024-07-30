package com.ademozalp.library_service.dto

data class AddBookRequest @JvmOverloads constructor(
    val id: String, // libraryId
    val isbn:String // book isbn

)
