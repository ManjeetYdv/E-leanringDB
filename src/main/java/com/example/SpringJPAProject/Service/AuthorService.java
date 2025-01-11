package com.example.SpringJPAProject.Service;

import com.example.SpringJPAProject.Entity.Author;
import com.example.SpringJPAProject.Exception.AuthorNotFoundException;
import com.example.SpringJPAProject.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // Create a new author
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Get all authors
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Get author by username(id)
    public Author getAuthorByUsername(String username) {
        return authorRepository.findById(username) // Use username as the primary key
                .orElseThrow(() -> new AuthorNotFoundException("Author Not Found with username: " + username));
    }

    // Update author by username
    public Author updateAuthor(String username, Author updatedAuthor) {
        // Check if the author exists
        Author existingAuthor = getAuthorByUsername(username);
        existingAuthor.setName(updatedAuthor.getName());
        existingAuthor.setEmail(updatedAuthor.getEmail()); // Assuming Email can be updated
        // Add other fields as necessary
        return authorRepository.save(existingAuthor);
    }

    // Delete author by username
    public void deleteAuthor(String username) {
        // Check if the author exists
        Author author = getAuthorByUsername(username);
        authorRepository.delete(author);
    }

    // Find authors by name (Example for custom query)
    public List<Author> findAuthorsByName(String name) {
        return authorRepository.findAll()
                .stream()
                .filter(author -> author.getName().equalsIgnoreCase(name))
                .toList();
    }

    // Count total authors
    public long countAuthors() {
        return authorRepository.count();
    }
}
