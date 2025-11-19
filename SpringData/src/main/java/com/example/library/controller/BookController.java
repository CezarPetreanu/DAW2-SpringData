package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Category;
import com.example.library.model.User;
import com.example.library.repository.CategoryRepository;
import com.example.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Book> search(@RequestParam String title) {
        return bookService.searchByTitle(title);
    }

    @GetMapping("/category/{categoryId}")
    public Page<Book> findByCategory(@PathVariable Long categoryId,
                                     @RequestParam int page,
                                     @RequestParam int size,
                                     CategoryRepository categoryRepository) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        return bookService.findByCategory(category, page, size);
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}