package com.example.Book_author.M_M.repository;

import com.example.Book_author.M_M.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepos extends JpaRepository<Book, Integer> {

}
