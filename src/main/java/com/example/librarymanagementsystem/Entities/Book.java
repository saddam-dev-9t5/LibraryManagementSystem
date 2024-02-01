package com.example.librarymanagementsystem.Entities;

import com.example.librarymanagementsystem.Enums.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(unique = true)
    private String bookName;

    private Genre bookGenre;

    private int noOfPage;

    private int price;

    private Date publishDate;

    private boolean isAvailable;

    @JoinColumn
    @ManyToOne
    private Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    public List<Transaction> transactionList = new ArrayList<>();

    public Book(String bookName, Genre bookGenre, int noOfPage, int price, Date publishDate) {
        this.bookName = bookName;
        this.bookGenre = bookGenre;
        this.noOfPage = noOfPage;
        this.price = price;
        this.publishDate = publishDate;
    }
}
