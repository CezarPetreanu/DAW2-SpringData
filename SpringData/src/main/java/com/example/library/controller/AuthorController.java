package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.Category;
import com.example.library.repository.CategoryRepository;
import com.example.library.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public Author create(@RequestBody Author author) {
        return authorService.save(author);
    }

    @PostMapping("/{id}/books")
    public ResponseEntity<Author> addBookToAuthor(@PathVariable Long id, @RequestBody Book book) {
        Author updatedAuthor = authorService.addBookToAuthor(id, book);
        return ResponseEntity.ok(updatedAuthor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getById(@PathVariable Long id) {
        return authorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Author> search(@RequestParam String name) {
        return authorService.findByNameContainingIgnoreCase(name);
    }

    @GetMapping
    public List<Author> findAll() {
        return authorService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorService.deleteById(id);
    }
}