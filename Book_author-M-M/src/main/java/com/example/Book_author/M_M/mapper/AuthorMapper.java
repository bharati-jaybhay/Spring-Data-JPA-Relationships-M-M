package com.example.Book_author.M_M.mapper;

import com.example.Book_author.M_M.Dto.AuthorDto;
import com.example.Book_author.M_M.Dto.BookDto;
import com.example.Book_author.M_M.Dto.DemoDto;
import com.example.Book_author.M_M.entity.Author;
import com.example.Book_author.M_M.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class AuthorMapper {

    public static Author AuthorDtoToAuthor(AuthorDto authorDto, Author author){
        author.setAuthorId(authorDto.getAuthorId());
        author.setAuthorName(authorDto.getAuthorName());
        author.setPhone(authorDto.getPhone());
        return author;
    }

    public static AuthorDto AuthorToAuthorDto(Author author, AuthorDto authorDto){
        authorDto.setAuthorId(author.getAuthorId());
        authorDto.setAuthorName(author.getAuthorName());
        authorDto.setPhone(author.getPhone());
        return authorDto;
    }

    // for demoDto

    public static DemoDto convertAuthorToAuthorWithAuthorDto(Author author, DemoDto demoDto) {

        demoDto.setAuthorName(author.getAuthorName());
        demoDto.setPhone(author.getPhone());
        demoDto.setAuthorId(author.getAuthorId());

        List<Book> books = author.getBooks();
        List<BookDto> bookDtos = new ArrayList<>();

        BookDto bookDto = null;
        for (Book book : books) {
            bookDto = BookMapper.mapBookToBookDto(book, new BookDto());
            bookDtos.add(bookDto);
        }
        demoDto.setBooks(bookDtos);
        return demoDto;

    }
}
