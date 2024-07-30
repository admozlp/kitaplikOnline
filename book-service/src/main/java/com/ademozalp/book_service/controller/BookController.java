package com.ademozalp.book_service.controller;

import com.ademozalp.book_service.dto.BookDto;
import com.ademozalp.book_service.dto.BookIdDto;
import com.ademozalp.book_service.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBook(){
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookIdDto> getByIsbn(@PathVariable(name = "isbn") String isbn){
        return ResponseEntity.ok().body(bookService.getByIsbn(isbn));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok().body(bookService.getById(id));
    }
}
