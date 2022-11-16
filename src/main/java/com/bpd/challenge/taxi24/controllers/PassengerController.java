package com.bpd.challenge.taxi24.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bpd.challenge.taxi24.models.Passenger;
import com.bpd.challenge.taxi24.services.PassengerService;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService service;

    @GetMapping
    public Page<Passenger> retrieveAllPassengers(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Passenger retrieveDriver(
            @PathVariable String id) throws ResponseStatusException {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger not found"));
    }

}
