package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entities.Book;
import com.example.librarymanagementsystem.Entities.LibraryCard;
import com.example.librarymanagementsystem.Entities.Transaction;
import com.example.librarymanagementsystem.Enums.TransactionStatus;
import com.example.librarymanagementsystem.Enums.TransactionType;
import com.example.librarymanagementsystem.Exceptions.BookNotAvailableException;
import com.example.librarymanagementsystem.Exceptions.BookNotFoundException;
import com.example.librarymanagementsystem.Exceptions.CardNotFoundException;
import com.example.librarymanagementsystem.Exceptions.CardReachedMaximumLimitException;
import com.example.librarymanagementsystem.Repository.BookRepository;
import com.example.librarymanagementsystem.Repository.CardRepository;
import com.example.librarymanagementsystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BookRepository bookRepository;

    public String issueBook(int cardId, int bookId) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(TransactionStatus.ONGOING);
        transaction.setTransactionType(TransactionType.ISSUED);
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);
        if(optionalLibraryCard.isEmpty()) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new CardNotFoundException("Invalid card ID given");
        }
        LibraryCard libraryCard = optionalLibraryCard.get();
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new BookNotFoundException("Invalid book ID given");
        }
        Book book = optionalBook.get();
        if(book.isAvailable() == Boolean.FALSE) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new BookNotAvailableException("Book is not available");
        }
        if(libraryCard.getNoOfBooksIssued() >= LibraryCard.MAX_NO_OF_ALLOWED_BOOKS) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new CardReachedMaximumLimitException("You reached the maximum limit.");
        }
        book.setAvailable(Boolean.FALSE);
        libraryCard.setNoOfBooksIssued(libraryCard.getNoOfBooksIssued()+1);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setBook(book);
        transaction.setLibraryCard(libraryCard);
        transactionRepository.save(transaction);
        return "Transaction has been saved";
    }
}
