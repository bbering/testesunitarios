package com.application.testesunitarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.testesunitarios.model.Book;
import com.application.testesunitarios.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public ResponseEntity<?> listBooks() {
        List<Book> bookList = bookService.listBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> findBookById(@PathVariable Long id) {
        Book foundBook = bookService.returnBookById(id);
        return new ResponseEntity<>(foundBook, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(book, id);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @PutMapping("/update/borrow/{id}")
    public ResponseEntity<?> borrowBook(@PathVariable Long id) {
        Book borrowedBook = bookService.borrowBook(id);
        return new ResponseEntity<>(borrowedBook, HttpStatus.OK);
    }

    @PutMapping("/update/bookHasReturned/{id}")
    public ResponseEntity<?> bookHasReturned(@PathVariable Long id) {
        Book returnedBook = bookService.bookHasReturned(id);
        return new ResponseEntity<>(returnedBook, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}