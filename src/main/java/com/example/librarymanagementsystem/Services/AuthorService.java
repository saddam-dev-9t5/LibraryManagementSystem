package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entities.Author;
import com.example.librarymanagementsystem.Repository.AuthorRepository;
import com.example.librarymanagementsystem.RequestDtos.AuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public String addAuthor(AuthorRequest authorRequest) throws Exception {
        try {
            Author newAuthor = new Author(authorRequest.getAuthorName(), authorRequest.getAuthorAge(), authorRequest.getEmailId());
            newAuthor = authorRepository.save(newAuthor);
            return "Author '" + newAuthor.getAuthorName() + "' has been created";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

}
