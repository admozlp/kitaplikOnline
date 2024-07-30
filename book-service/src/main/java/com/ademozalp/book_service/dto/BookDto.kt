package com.ademozalp.book_service.dto

import com.ademozalp.book_service.model.Book


data class BookDto @JvmOverloads constructor(
    val id: BookIdDto? = null,
    val title: String,
    val bookYear: Int,
    val author: String,
    val pressName: String,
    val isbn: String
){
    companion object {
        @JvmStatic
        fun convertToDTo(book:Book) : BookDto {
            return BookDto(
                book.id?.let { BookIdDto.convertToBookIdDto(it, book.isbn) },
                book.title,
                book.bookYear,
                book.author,
                book.pressName,
                book.isbn
            )
        }
    }
}

