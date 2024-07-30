package com.ademozalp.library_service.model

import jakarta.persistence.*


@Entity
@Table(name = "libraries")
data class Library @JvmOverloads constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id:String? = "",

    @ElementCollection
    val bookList: List<String> = ArrayList(),
)
