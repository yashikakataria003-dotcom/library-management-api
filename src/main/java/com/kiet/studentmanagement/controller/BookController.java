package com.kiet.studentmanagement.controller;

import com.kiet.studentmanagement.model.Book;
import com.kiet.studentmanagement.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable int bookId) {
        Book book = bookService.getBookById(bookId);
        if (book == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Book not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book createdBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable int bookId, @RequestBody Book updatedBook) {
        Book book = bookService.updateBook(bookId, updatedBook);
        if (book == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Book not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Map<String, String>> deleteBook(@PathVariable int bookId) {
        boolean deleted = bookService.deleteBook(bookId);
        Map<String, String> response = new HashMap<>();
        if (!deleted) {
            response.put("message", "Book not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.put("message", "Book deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByCategory(@RequestParam String category) {
        List<Book> results = bookService.searchByCategory(category);
        if (results.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "No books found in category: " + category);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(results);
    }

    @GetMapping("/published")
    public ResponseEntity<?> getBooksPublishedFromYear(@RequestParam int fromYear) {
        List<Book> results = bookService.findPublishedFromYear(fromYear);
        if (results.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "No books found published from year " + fromYear + " onward");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(results);
    }
}
