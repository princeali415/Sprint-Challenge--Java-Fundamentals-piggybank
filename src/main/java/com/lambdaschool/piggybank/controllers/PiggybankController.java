package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Piggybank;
import com.lambdaschool.piggybank.repositories.PiggybankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PiggybankController
{

    @Autowired
    PiggybankRepository piggybankRepository;

    // http://localhost:2019/total
    @GetMapping(value = "/total", produces = "application/json")
    public ResponseEntity<?> calculateTotal()
    {
        List<Piggybank> myList = new ArrayList<>();
        piggybankRepository.findAll().iterator().forEachRemaining(myList::add);
        double total = 0.0;
        String message;
        for (Piggybank coin : myList){
            total += coin.getValue() * coin.getQuantity();
            if(coin.getQuantity() > 1){
                message = coin.getQuantity() + " " + coin.getNameplural();
            }
            else {
                message = coin.getQuantity() + " " + coin.getName();
            }
            System.out.println(message);
        }
        String totalMessage = "The piggy bank holds $" + total;
        System.out.println(totalMessage);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}
