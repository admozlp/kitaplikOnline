package com.ademozalp.library_service.service;

import com.ademozalp.library_service.client.BookServiceClient;
import com.ademozalp.library_service.dto.AddBookRequest;
import com.ademozalp.library_service.dto.BookDto;
import com.ademozalp.library_service.dto.BookIdDto;
import com.ademozalp.library_service.dto.LibraryDto;
import com.ademozalp.library_service.exception.LibraryNotFoundException;
import com.ademozalp.library_service.model.Library;
import com.ademozalp.library_service.repository.LibraryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final BookServiceClient bookService;
    private final Logger log = LoggerFactory.getLogger(LibraryService.class);

    public LibraryService(LibraryRepository libraryRepository, BookServiceClient bookService) {
        this.libraryRepository = libraryRepository;
        this.bookService = bookService;
    }

    public LibraryDto createLibrary() {

        Library library = libraryRepository.save(new Library());
        log.info("Library created: " + library.getId());

        return new LibraryDto(library.getId());
    }

    public void addBookToLibrary(AddBookRequest request) {
        Library library = libraryRepository.findById(request.getId())
                .orElseThrow(() -> new LibraryNotFoundException("Lİbrary not found: " + request.getId()));

        log.info("Book added to library: " + request.getId());

        BookIdDto bookIdDto = bookService.getByIsbn(request.getIsbn()).getBody();

        library.getBookList().add(bookIdDto.getId());


        libraryRepository.save(library);
    }


    public LibraryDto getAllBooksByLibraryId(String id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException("Library not found exception: " + id));


        List<BookDto> list = library.getBookList().stream()
                .map(bookId -> bookService.getById(bookId).getBody())
                .toList();

        log.info("Library found: " + library.getId());
        return new LibraryDto(library.getId(), list);
    }


}
