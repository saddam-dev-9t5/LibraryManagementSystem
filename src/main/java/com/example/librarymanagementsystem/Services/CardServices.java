package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entities.LibraryCard;
import com.example.librarymanagementsystem.Entities.Student;
import com.example.librarymanagementsystem.Enums.CardStatus;
import com.example.librarymanagementsystem.Repository.CardRepository;
import com.example.librarymanagementsystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardServices {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public String addFreshCard() {
        LibraryCard newCard = new LibraryCard();
        newCard.setCardStatus(CardStatus.NEW);
        newCard.setNoOfBooksIssued(0);
        LibraryCard createdCard = cardRepository.save(newCard);
        return "New Card with ID " + createdCard.getCard_id() + " has been created";
    }

    public String associateCardAndStudent(Integer studentId, Integer cardId) throws Exception{
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);
        if(optionalLibraryCard.isEmpty()) {
            throw new Exception("Invalid Card Id");
        }
        LibraryCard libraryCard = optionalLibraryCard.get();

        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(optionalStudent.isEmpty()) {
            throw new Exception("Invalid Student Id");
        }
        Student student = optionalStudent.get();

        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setNoOfBooksIssued(0);
        libraryCard.setStudent(student);
        cardRepository.save(libraryCard);


        return "Card assigned to student";
    }
}
