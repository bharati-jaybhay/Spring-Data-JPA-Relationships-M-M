package com.example.Book_author.M_M.controller;

import com.example.Book_author.M_M.Dto.DemoDto;
import com.example.Book_author.M_M.entity.Author;
import com.example.Book_author.M_M.entity.Book;
import com.example.Book_author.M_M.mapper.AuthorMapper;
import com.example.Book_author.M_M.repository.AuthorRepos;
import com.example.Book_author.M_M.repository.BookRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {
    @Autowired
    private BookRepos bookRepos;

    @Autowired
    private AuthorRepos authorRepos;

    @Autowired
    private AuthorRepos authorRepository;

    @PostMapping("/assignBook-To-Author")
    public String addBookToAuthor(@RequestParam int bookId, @RequestParam int authorId){

        Author author=authorRepos.findById(authorId).orElseThrow(() -> new RuntimeException("Student not found"));

        Book book=bookRepos.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found!!"));
        author.addBook(book);
        authorRepos.save(author);
        return "Book assigned author successfully";

    }

    @GetMapping("get-AuthorWithBook/{authorId}")
    public DemoDto getBookWithAuthor(@PathVariable int authorId){
        Author author=authorRepository.findBookWithAuthor(authorId).orElseThrow(() -> new RuntimeException("Author not found!!"));
        DemoDto demoDto= AuthorMapper.convertAuthorToAuthorWithAuthorDto(author, new DemoDto());

        return demoDto;
    }
}
