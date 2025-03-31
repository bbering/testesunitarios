package com.application.testesunitarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
