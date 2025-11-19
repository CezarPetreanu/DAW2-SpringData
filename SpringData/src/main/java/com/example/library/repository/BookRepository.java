package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.model.Author;
import com.example.library.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    Page<Book> findByCategory(Category category, Pageable pageable);

    List<Book> findByAuthor(Author author);

    List<Book> findByAvailableCopiesGreaterThan(int minCopies);
}
