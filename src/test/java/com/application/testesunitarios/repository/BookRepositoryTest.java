package com.application.testesunitarios.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.application.testesunitarios.model.Book;

@SpringBootTest
@ActiveProfiles("test")
public class BookRepositoryTest {

    // essa classe contem testes de integracao, utilizando um banco de dados em
    // memoria h2 para simular um banco em producao

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book("Test Title", "Test Author", 0, 0);
    }

    @Test
    @DisplayName("Should save correctly the book on DB")
    public void testSaveBook() {
        bookRepository.save(book);

        assertNotNull(book.getId());
        assertEquals("Test Title", book.getTitle());
        assertEquals("Test Author", book.getAuthor());
        assertEquals(0, book.getReleaseYear());
        assertEquals(0, book.getQuantityAvailable());
    }

    @Test
    @DisplayName("Should find the correct book by its ID on DB")
    public void findBookById() {
        Book savedBook = bookRepository.save(book);

        Book foundBook = bookRepository.findById(savedBook.getId())
                .orElseThrow(() -> new RuntimeException("Nenhum livro encontrado com o ID"));

        assertNotNull(foundBook);
        assertEquals(savedBook.getId(), foundBook.getId());
        assertEquals(savedBook.getTitle(), foundBook.getTitle());
        assertEquals(savedBook.getAuthor(), foundBook.getAuthor());
        assertEquals(savedBook.getQuantityAvailable(), foundBook.getQuantityAvailable());
        assertEquals(savedBook.getReleaseYear(), foundBook.getReleaseYear());
    }
}