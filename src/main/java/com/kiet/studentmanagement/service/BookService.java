package com.kiet.studentmanagement.service;

import com.kiet.studentmanagement.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final List<Book> bookList = new ArrayList<>();

    public BookService() {
        bookList.add(new Book(201, "Effective Java", "Joshua Bloch", "Programming", 2018, true));
        bookList.add(new Book(202, "Database System Concepts", "Abraham Silberschatz", "Database", 2019, true));
        bookList.add(new Book(203, "Operating System Concepts", "Abraham Silberschatz", "Systems", 2018, false));
        bookList.add(new Book(204, "Introduction to Algorithms", "Thomas H. Cormen", "Algorithms", 2022, true));
        bookList.add(new Book(205, "Head First Design Patterns", "Eric Freeman", "Programming", 2020, true));
    }

    public List<Book> getAllBooks() {
        return bookList;
    }

    public Book getBookById(int bookId) {
        for (Book book : bookList) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public Book addBook(Book book) {
        bookList.add(book);
        return book;
    }

    public Book updateBook(int bookId, Book updatedBook) {
        for (Book existingBook : bookList) {
            if (existingBook.getBookId() == bookId) {
                existingBook.setTitle(updatedBook.getTitle());
                existingBook.setAuthor(updatedBook.getAuthor());
                existingBook.setCategory(updatedBook.getCategory());
                existingBook.setPublicationYear(updatedBook.getPublicationYear());
                existingBook.setAvailable(updatedBook.isAvailable());
                return existingBook;
            }
        }
        return null;
    }

    public boolean deleteBook(int bookId) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getBookId() == bookId) {
                bookList.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Book> searchByCategory(String category) {
        List<Book> matchedBooks = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getCategory() != null && book.getCategory().equalsIgnoreCase(category)) {
                matchedBooks.add(book);
            }
        }
        return matchedBooks;
    }

    public List<Book> findPublishedFromYear(int fromYear) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getPublicationYear() >= fromYear) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }
}
