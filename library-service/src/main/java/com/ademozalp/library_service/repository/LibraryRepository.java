package com.ademozalp.library_service.repository;

import com.ademozalp.library_service.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, String> {
}
