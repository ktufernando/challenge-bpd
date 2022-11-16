package com.bpd.challenge.taxi24.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bpd.challenge.taxi24.models.Passenger;
import com.bpd.challenge.taxi24.repositories.PassengerRepository;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository repository;

    public Page<Passenger> findAll(Pageable page) {
        return repository.findAll(page);
    }

    public Optional<Passenger> findById(String id) {
        return repository.findById(id);
    }

}
