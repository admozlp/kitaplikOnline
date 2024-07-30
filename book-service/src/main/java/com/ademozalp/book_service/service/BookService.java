package com.ademozalp.book_service.service;


import com.ademozalp.book_service.dto.BookDto;
import com.ademozalp.book_service.dto.BookIdDto;
import com.ademozalp.book_service.exception.BookNotFoundException;
import com.ademozalp.book_service.model.Book;
import com.ademozalp.book_service.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private static final Logger log = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<BookDto> getAllBooks(){
        return bookRepository.findAll().stream()
                .map(BookDto::convertToDTo)
                .toList();
    }

    public BookIdDto getByIsbn(String isbn){
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book not found by isbn: " + isbn));

        return BookIdDto.convertToBookIdDto(book.getId(), book.getIsbn());
    }

    public BookDto getById(String id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found by id:" + id));

        log.info("Book found: " + book.getId());

        return BookDto.convertToDTo(book);
    }
}
