package com.application.testesunitarios.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "releaseYear")
    private int releaseYear;

    @Column(name = "quantityAvailable")
    private int quantityAvailable;

    public Book(String title, String author, int releaseYear, int quantityAvailable) {
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
        this.quantityAvailable = quantityAvailable;
    }

    public Book(){
        
    }
}
