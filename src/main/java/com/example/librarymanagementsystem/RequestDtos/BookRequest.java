package com.example.librarymanagementsystem.RequestDtos;

import com.example.librarymanagementsystem.Enums.Genre;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookRequest {

    private String bookName;

    private Genre bookGenre;

    private int noOfPage;

    private int price;

    private Date publishDate;

    private int authorId;

}
