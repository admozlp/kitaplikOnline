package com.ademozalp.book_service;

import com.ademozalp.book_service.model.Book;
import com.ademozalp.book_service.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BookServiceApplication implements CommandLineRunner {
	private final BookRepository bookRepository;

	public BookServiceApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
	}

    public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		bookRepository.saveAll(List.of(
				new Book("Semerkant", 2000, "Ömer Hayyam", "5. baskı", "SMR100"),
				new Book("Olasılıksız", 2010, "Adam Fawer", "10. baskı", "OLS200"),
				new Book("Küçük Prens", 2020, "Anonim", "15. baskı", "KCP300"),
				new Book("Hayvan Çiftliği", 2024, "George Orwell", "100. baskı", "HYC400")
		));
	}
}
