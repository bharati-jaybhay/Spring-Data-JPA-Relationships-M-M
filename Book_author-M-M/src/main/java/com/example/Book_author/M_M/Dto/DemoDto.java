package com.example.Book_author.M_M.Dto;

import lombok.Data;

import java.util.List;

@Data
public class DemoDto {

    private int authorId;

    private String authorName;

    private String phone;

    private List<BookDto> books;
}
