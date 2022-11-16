package com.bpd.challenge.taxi24.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bpd.challenge.taxi24.models.Trip;
import com.bpd.challenge.taxi24.services.TripService;
import com.bpd.challenge.taxi24.types.TripStatusType;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService service;

    @PostMapping
    Trip saveTrip(@RequestBody Trip tripIn) {
        return service.save(tripIn);
    }

    @PutMapping("/{id}/status/{type}")
    Trip updateTripStatus(@PathVariable String id, @PathVariable TripStatusType type) {
        return service.updateStatus(id, type);
    }

    @GetMapping
    public Page<Trip> retrieveAllTrips(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/status/{type}")
    public List<Trip> retrieveByStatus(
            @PathVariable TripStatusType type) {
        return service.findByStatus(type);
    }

}
