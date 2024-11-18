package com.example.Book_author.M_M.repository;

import com.example.Book_author.M_M.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorRepos extends JpaRepository<Author, Integer> {

    // Custom query to fetch a student and their associated courses
    @Query("From Author a JOIN FETCH a.books WHERE a.authorId= :authorId")
    Optional<Author> findBookWithAuthor(int authorId);


}
