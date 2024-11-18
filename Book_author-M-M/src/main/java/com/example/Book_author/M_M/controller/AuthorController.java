package com.example.Book_author.M_M.controller;

import com.example.Book_author.M_M.Dto.AuthorDto;
import com.example.Book_author.M_M.entity.Author;
import com.example.Book_author.M_M.mapper.AuthorMapper;
import com.example.Book_author.M_M.repository.AuthorRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorRepos authorRepos;

    @PostMapping("/save")
    public AuthorDto saveAuthor(@RequestBody AuthorDto authorDto){
        Author author= AuthorMapper.AuthorDtoToAuthor(authorDto, new Author());
        authorRepos.save(author);
        authorDto.setAuthorId(author.getAuthorId());
        return authorDto;
    }

    @GetMapping("/getAuthors")
    public List<AuthorDto> getAuthors() {
        List<Author> authors = authorRepos.findAll();
        List<AuthorDto> authorDtos = new ArrayList<>();

        for (Author author : authors) {
            AuthorDto authorDto = AuthorMapper.AuthorToAuthorDto(author, new AuthorDto());
            authorDtos.add(authorDto);
        }
        return authorDtos;
    }

    @GetMapping("/getAuthor/{authorId}")
    public AuthorDto getByAuthorId(@PathVariable int authorId) {
        Author author = authorRepos.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
        AuthorDto authorDto = AuthorMapper.AuthorToAuthorDto(author, new AuthorDto());
        return authorDto;
    }

    @DeleteMapping("/delete/{authorId}")
    public String deleteByID(@PathVariable int authorId){
        authorRepos.deleteById(authorId);
        return "Author is deleted";
    }

    @PutMapping("/updateAuthor/{authorId}")
    public AuthorDto updateAuthorById(@RequestBody AuthorDto authorDto, @PathVariable int authorId) {
        Author existingAuthor = authorRepos.findById(authorId).orElse(null);

        if (existingAuthor == null) {
            return null; // Return null if the author does not exist
        }
        if (authorDto.getAuthorName() != null) {
            existingAuthor.setAuthorName(authorDto.getAuthorName());
        }
        if (authorDto.getPhone() != null) {
            existingAuthor.setPhone(authorDto.getPhone());
        }

        Author updatedAuthor = authorRepos.save(existingAuthor);
        return AuthorMapper.AuthorToAuthorDto(updatedAuthor, new AuthorDto());
    }


}
