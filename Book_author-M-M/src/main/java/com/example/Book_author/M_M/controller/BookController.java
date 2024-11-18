package com.example.Book_author.M_M.controller;

import com.example.Book_author.M_M.Dto.BookDto;
import com.example.Book_author.M_M.entity.Book;
import com.example.Book_author.M_M.mapper.BookMapper;
import com.example.Book_author.M_M.repository.BookRepos;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookRepos bookRepos;

    @PostMapping("/saveBook")
    public BookDto saveBook(@RequestBody BookDto bookDto) {
        Book book = BookMapper.mapBookDtoToBook(bookDto, new Book());
        bookRepos.save(book);
        bookDto.setBookId(book.getBookId());
        return bookDto;

    }

    @GetMapping("/getBooks")
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepos.findAll();
        List<BookDto> bookDtos = new ArrayList<>();

        for (Book book : books) {
            BookDto bookDto = BookMapper.mapBookToBookDto(book, new BookDto());
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }

    @GetMapping("/getbook/{bookId}")
    public BookDto getBookById(@PathVariable int bookId) {
        Book book = bookRepos.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found!! "));

        BookDto bookDto = BookMapper.mapBookToBookDto(book, new BookDto());
        return bookDto;
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public String deleteBookById(@PathVariable int bookId) {
        bookRepos.deleteById(bookId);
        return "Book deleted";

    }

    @PutMapping("/updatedBook/{bookId}")
    public BookDto updateBookById(@PathVariable int bookId, @RequestBody BookDto bookDto) {
        Book existingBook = bookRepos.findById(bookId).orElse(null);

        if (existingBook == null) {
            return null; // Return null or an appropriate error response if the book isn't found
        }
        if (bookDto.getBookName() != null) {
            existingBook.setBookName(bookDto.getBookName());
        }
        if (bookDto.getDescription() != null) {
            existingBook.setDescription(bookDto.getDescription());
        }

        // Save the updated book entity
        Book updatedBook = bookRepos.save(existingBook);

        // Map the updated book entity to BookDto for response
        return BookMapper.mapBookToBookDto(updatedBook, new BookDto());
    }

}
