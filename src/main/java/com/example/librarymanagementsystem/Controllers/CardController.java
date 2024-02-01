package com.example.librarymanagementsystem.Controllers;

import com.example.librarymanagementsystem.Services.CardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardServices cardServices;

    @GetMapping("add")
    public ResponseEntity<String> addCard() {
        String result = cardServices.addFreshCard();
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/associateCardAndStudent")
    public ResponseEntity<String> associateCardAndStudent(@RequestParam("studentId")Integer studentId, @RequestParam("cardId")Integer cardId) throws Exception {
        try {
            String result = cardServices.associateCardAndStudent(studentId, cardId);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
