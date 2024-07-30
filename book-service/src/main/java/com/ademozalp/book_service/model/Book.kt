package com.ademozalp.book_service.model

import jakarta.persistence.*


@Entity
data class Book @JvmOverloads constructor(
     @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = "",
    val title: String,
    val bookYear: Int,
    val author: String,
    val pressName: String,
    val isbn: String
){
    constructor() : this("", "", 0, "", "", "")
}
