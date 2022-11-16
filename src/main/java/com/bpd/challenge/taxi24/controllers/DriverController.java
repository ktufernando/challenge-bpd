package com.bpd.challenge.taxi24.controllers;

import java.util.List;
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

import com.bpd.challenge.taxi24.models.Driver;
import com.bpd.challenge.taxi24.services.DriverService;
import com.bpd.challenge.taxi24.types.DriverStatusType;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private DriverService service;

    @GetMapping
    public Page<Driver> retrieveAllDrivers(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{lon}/{lat}/{distance}")
    public List<Driver> retrieveDriversArround(
            @PathVariable double lon,
            @PathVariable double lat,
            @PathVariable double distance) {
        return service.findOnlineAround(lon, lat, distance);
    }

    @GetMapping("/{lon}/{lat}")
    public List<Driver> retrieveFirst3DriversArround(
            @PathVariable double lon,
            @PathVariable double lat) {
        return service.findFirst3OnlineLocationNear(lon, lat);
    }

    @GetMapping("/{id}")
    public Driver retrieveDriver(
            @PathVariable String id) throws ResponseStatusException {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger not found"));
    }

    @GetMapping("/status/{type}")
    public List<Driver> retrieveDriver(
            @PathVariable DriverStatusType type) {
        return service.findByStatus(type);
    }

}
