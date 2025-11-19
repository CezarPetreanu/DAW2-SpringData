package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.Category;
import com.example.library.repository.BookRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @CacheEvict(value = "book", key = "#book.id")
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Cacheable(value = "book", key = "#id")
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Cacheable(value = "book", key = "#title")
    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Cacheable(value = "book", key = "#category")
    public Page<Book> findByCategory(Category category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findByCategory(category, pageable);
    }

    @Cacheable(value = "book", key = "#author")
    public List<Book> findByAuthor(Author author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findAvailable(int minCopies) {
        return bookRepository.findByAvailableCopiesGreaterThan(minCopies);
    }

    @CacheEvict(value = "book", key = "#id")
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}