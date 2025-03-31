package com.application.testesunitarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.testesunitarios.model.Book;
import com.application.testesunitarios.repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public Book createBook(Book book) {
        Book bookToSave = new Book();
        bookToSave.setId(book.getId());
        bookToSave.setAuthor(book.getAuthor());
        bookToSave.setQuantityAvailable(book.getQuantityAvailable());
        bookToSave.setReleaseYear(book.getReleaseYear());
        bookToSave.setTitle(book.getTitle());
        bookRepository.save(bookToSave);
        return bookToSave;
    }

    public List<Book> listBooks() {
        List<Book> availableBooks = bookRepository.findAll();
        return availableBooks;
    }

    @Transactional
    public Book updateBook(Book book, Long id) {
        Book bookToUpdate = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum livro encontrado com o id: " + id));
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setReleaseYear(book.getReleaseYear());
        bookToUpdate.setQuantityAvailable(book.getQuantityAvailable());
        bookToUpdate.setId(book.getId());
        bookToUpdate.setAuthor(book.getAuthor());
        bookRepository.save(bookToUpdate);
        return bookToUpdate;
    }

    public void deleteBook(Long id) {
        Optional<Book> bookToDelete = bookRepository.findById(id);

        if (!bookToDelete.isPresent()) {
            throw new RuntimeException("Nenhum livro encontrado com o id: " + id);
        } else {
            bookRepository.deleteById(id);
        }
    }

    @Transactional
    public Book borrowBook(Long id) {
        Book bookToBorrow = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum livro encontrado com o id: " + id));
        bookToBorrow.setQuantityAvailable(bookToBorrow.getQuantityAvailable() - 1);
        return bookToBorrow;
    }

    @Transactional
    public Book bookHasReturned(Long id) {
        Book returnedBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum livro encontrado com o id: " + id));
        returnedBook.setQuantityAvailable(returnedBook.getQuantityAvailable() + 1);
        return returnedBook;
    }
}
