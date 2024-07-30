package com.ademozalp.library_service.client;


import com.ademozalp.library_service.dto.BookDto;
import com.ademozalp.library_service.dto.BookIdDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service", path = "/api/v1/books")
public interface BookServiceClient {
    Logger log = LoggerFactory.getLogger(BookServiceClient.class);

    @GetMapping("/isbn/{isbn}")
    @CircuitBreaker(name = "book-service-get-by-isbn", fallbackMethod = "saveDefaultBook")
    ResponseEntity<BookIdDto> getByIsbn(@PathVariable(value = "isbn") String isbn);

    default ResponseEntity<BookIdDto> saveDefaultBook(String isbn, Throwable throwable) {
        log.info("Fallback method is called for getByIsbn method with ISBN: {}", isbn);
        return ResponseEntity.ok(new BookIdDto("1",  "Default ISBN"));
    }

    @GetMapping("/id/{id}")
    ResponseEntity<BookDto> getById(@PathVariable(value = "id") String id);
}
