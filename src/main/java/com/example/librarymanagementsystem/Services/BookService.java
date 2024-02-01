package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entities.Author;
import com.example.librarymanagementsystem.Entities.Book;
import com.example.librarymanagementsystem.Repository.AuthorRepository;
import com.example.librarymanagementsystem.Repository.BookRepository;
import com.example.librarymanagementsystem.RequestDtos.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;


    public String addBook(BookRequest bookRequest) {
        Book newBook = new Book(bookRequest.getBookName(), bookRequest.getBookGenre(), bookRequest.getNoOfPage(), bookRequest.getPrice(), bookRequest.getPublishDate());

        Optional<Author> authorOptional = authorRepository.findById(bookRequest.getAuthorId());
        if(authorOptional.isEmpty()) {
            return "Invalid Author Id";
        }

        Author author = authorOptional.get();
        author.setNoOfBook(author.getNoOfBook()+1);

        newBook.setAuthor(author);
        author.getBookList().add(newBook);
        bookRepository.save(newBook);
        return "Book '" + newBook.getBookName() + "' added to DB";
    }
}
