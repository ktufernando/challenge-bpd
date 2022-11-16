package com.bpd.challenge.taxi24.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bpd.challenge.taxi24.models.Driver;
import com.bpd.challenge.taxi24.models.Invoice;
import com.bpd.challenge.taxi24.models.Passenger;
import com.bpd.challenge.taxi24.models.Trip;
import com.bpd.challenge.taxi24.repositories.DriverRepository;
import com.bpd.challenge.taxi24.repositories.PassengerRepository;
import com.bpd.challenge.taxi24.repositories.TripRepository;
import com.bpd.challenge.taxi24.types.TripStatusType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TripService {

    @Autowired
    private TripRepository repository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private InvoiceService invoiceService;

    public Trip save(Trip trip) throws ResponseStatusException {

        Driver driver = driverRepository.findById(trip.getDriver().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found"));
        Passenger passenger = passengerRepository.findById(trip.getPassenger().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger not found"));

        trip.setCreatedAt(LocalDateTime.now());
        trip.setStatus(TripStatusType.INITIALIZED);
        trip = repository.save(trip);

        if (driver.getTrips() == null) {
            driver.setTrips(new HashSet<Trip>());
        }
        driver.getTrips().add(trip);
        driverRepository.save(driver);

        if (passenger.getTrips() == null) {
            passenger.setTrips(new HashSet<Trip>());
        }
        passenger.getTrips().add(trip);
        passengerRepository.save(passenger);

        return trip;

    }

    public Trip updateStatus(String id, TripStatusType type) throws ResponseStatusException {

        log.info("Updating trip status to {}", type);

        Trip trip = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found"));

        trip.setUpdatedAt(LocalDateTime.now());
        trip.setStatus(type);

        if (type == TripStatusType.ACCEPTED_BY_DRIVER) {
            trip.setAcceptedByDriverAt(LocalDateTime.now());
        }
        if (type == TripStatusType.ON_TRIP) {
            trip.setInitAt(LocalDateTime.now());
        }
        if (type == TripStatusType.TRIP_COMPLETED) {
            trip.setEndAt(LocalDateTime.now());
            Invoice invoice = invoiceService.billing(trip);
            if (trip.getInvoices() == null) {
                trip.setInvoices(new HashSet<Invoice>());
            }
            trip.getInvoices().add(Invoice.builder().id(invoice.getId()).build());
        }
        if (type == TripStatusType.PAID) {
            trip.setPaid(true);
        }

        return repository.save(trip);
    }

    public Page<Trip> findAll(Pageable page) {
        return repository.findAll(page);
    }

    public List<Trip> findByStatus(TripStatusType status) {
        return repository.findByStatus(status);
    }

}
