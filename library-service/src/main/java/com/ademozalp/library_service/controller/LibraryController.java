package com.ademozalp.library_service.controller;


import com.ademozalp.library_service.dto.AddBookRequest;
import com.ademozalp.library_service.dto.LibraryDto;
import com.ademozalp.library_service.service.LibraryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/libraries")
@RefreshScope
public class LibraryController {
    @Value("${library.service.count}")
    private String count;

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping
    public ResponseEntity<LibraryDto> createLibrary() {
        return ResponseEntity.ok(libraryService.createLibrary());
    }

    @PutMapping
    public ResponseEntity<Void> addBookToLibrary(@RequestBody AddBookRequest request){
        libraryService.addBookToLibrary(request);
        return new  ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryDto> getAllBooksByLibraryId(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(libraryService.getAllBooksByLibraryId(id));
    }

    @GetMapping("/count")
    public ResponseEntity<String> getCount(){
        return ResponseEntity.ok("Library service count is: " + count);
    }
}
