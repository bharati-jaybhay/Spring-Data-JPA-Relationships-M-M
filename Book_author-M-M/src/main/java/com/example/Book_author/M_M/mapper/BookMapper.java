package com.example.Book_author.M_M.mapper;

import com.example.Book_author.M_M.Dto.BookDto;
import com.example.Book_author.M_M.entity.Book;

public class BookMapper {

    // Maps data from BookDto to Book
    public static Book mapBookDtoToBook(BookDto bookDto, Book book) {
        book.setBookId(bookDto.getBookId());
        book.setBookName(bookDto.getBookName());
        book.setDescription(bookDto.getDescription());
        return book;
    }

    // Maps data from Book to BookDto
    public static BookDto mapBookToBookDto(Book book, BookDto bookDto) {
        bookDto.setBookId(book.getBookId());
        bookDto.setBookName(book.getBookName());
        bookDto.setDescription(book.getDescription());
        return bookDto;
    }
}
