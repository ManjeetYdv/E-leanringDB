package com.example.SpringJPAProject.Controller;

import com.example.SpringJPAProject.Entity.Author;
import com.example.SpringJPAProject.Service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // Greeting endpoint
    @GetMapping("/greet")
    public String greeting(@RequestParam(defaultValue = "XYZ") String name) {
        return "Hello " + name;
    }

    // Get all authors
    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    // Get an author by ID
    @GetMapping("/{username}")
    public Author getAuthorById(@PathVariable String username) {
        return authorService.findById(username);
    }

    // Create a new author
    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    // Delete an author by ID
    @DeleteMapping("/{username}")
    public String deleteAuthor(@PathVariable String username) {
        authorService.deleteAuthor(username);
        return "Author with username:  " + username + " has been deleted successfully.";
    }

    // Find authors by name (case-insensitive search)
    @GetMapping("/search")
    public List<Author> findAuthorsByName(@RequestParam String name) {
        return authorService.findAuthorsByName(name);
    }

    // Count total authors
    @GetMapping("/count")
    public long countAuthors() {
        return authorService.countAuthors();
    }
}
