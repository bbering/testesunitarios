package com.application.testesunitarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.application.testesunitarios.model.Book;
import com.application.testesunitarios.repository.BookRepository;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create book successfully when everything is ok")
    void shouldCreateBookWhenDataIsValid() {
        // instanciando um novo objeto do tipo book
        Book book = new Book(1L, "Test title", "Test author", 1990, 4);

        // mockar o comportamento de salvamento para retornar um objeto do tipo livro
        // quando for chamado
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // chamar o metodo save para criar um objeto do tipo livro
        Book returnedBook = bookService.createBook(book);

        // verificar quantas vezes o repositorio foi chamado ao acionar o metodo save
        verify(bookRepository, times(1)).save(any(Book.class));

        // campo de asserts
        assertEquals(book.getTitle(), returnedBook.getTitle());
        assertEquals(book.getAuthor(), returnedBook.getAuthor());
        assertEquals(book.getQuantityAvailable(), returnedBook.getQuantityAvailable());
        assertEquals(book.getReleaseYear(), returnedBook.getReleaseYear());
        assertEquals(book.getId(), returnedBook.getId());
    }

    @Test
    @DisplayName("Should not create a book when data is not valid")
    public void shouldNotCreateBookWhenDataIsNotValid() {
        Book invalidBook = new Book(null, "Test author");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bookService.createBook(invalidBook));

        assertEquals("Title and author cant be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should list all the books")
    public void shouldListAllTheBooksIfTheresAny() {
        // mockando uma lista de books para teste
        List<Book> mockBooks = List.of(
                new Book(1L, "Test title", "Test author", 1990, 4),
                new Book(2L, "Test title 2", "Test author 2", 1991, 5),
                new Book(3L, "Test title 3", "Test author 3", 1992, 6));

        // assegurar que, quando o metodo findall for chamado, ele retorne uma lista de
        // objetos do tipo book
        when(bookRepository.findAll()).thenReturn(mockBooks);

        // lista de objetos do tipo book para realizar assertions
        List<Book> result = bookService.listBooks();

        // assercoes
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Test title", result.get(0).getTitle());
        assertEquals("Test title 2", result.get(1).getTitle());
        assertEquals("Test title 3", result.get(2).getTitle());

        // verificar que o repositorio é chamado apenas uma vez para o metodo findall
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should update book when data is valid")
    public void shouldUpdateBookWhenDataIsValid() {
        // mockando um book para usar no teste
        Book book = new Book(1L, "Test title", "Test author", 1990, 4);

        // quando o repository tiver o metodo findById chamado, deve retornar um objeto
        // book
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        // testando o metodo update da service com as assertions
        Book bookToUpdate = bookService.updateBook(book, 1L);
        assertEquals(bookToUpdate.getTitle(), book.getTitle());
        assertEquals(bookToUpdate.getAuthor(), book.getAuthor());
        assertEquals(bookToUpdate.getQuantityAvailable(), book.getQuantityAvailable());
        assertEquals(bookToUpdate.getId(), book.getId());
        assertEquals(bookToUpdate.getReleaseYear(), book.getReleaseYear());

        // verificando se o metodo findById é chamado uma só vez
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should not update the book if he wasnt found on DB")
    public void shouldNotUpdateTheBookIfHeDoesntExist() {
        // se nao houver um livro com o id recebido, retorna um objeto optional vazio
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.updateBook(new Book(), 1L),
                "Nenhum livro encontrado com o id: " + 1L);
    }
}