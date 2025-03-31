package com.application.testesunitarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.testesunitarios.model.Book;

public interface BookRepository extends JpaRepository <Book, Long> {

}
