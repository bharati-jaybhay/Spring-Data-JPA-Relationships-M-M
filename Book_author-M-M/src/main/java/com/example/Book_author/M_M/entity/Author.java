package com.example.Book_author.M_M.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;

    private String authorName;

    @Column(length = 15)
    private String phone;

    @ManyToMany(mappedBy = "authors", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        books.add(book);
        book.getAuthors().add(this);
    }
}
