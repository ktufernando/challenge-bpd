package com.bpd.challenge.taxi24.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpd.challenge.taxi24.models.Invoice;
import com.bpd.challenge.taxi24.models.Trip;
import com.bpd.challenge.taxi24.repositories.InvoiceRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    /*
     * FIXME: That service should be from another domain and could be started with
     * an event
     */
    public Invoice billing(Trip trip) {
        Invoice invoice = Invoice.builder().identification(generateIdentification()).baseRate(getBaseRate())
                .time(getAmountPerTime()).distance(getAmountPerDistance()).discount(getAmountDiscount())
                .subtotal(getSubtotal()).total(getSubtotal()).trip(Trip.builder().id(trip.getId()).build()).build();
        return repository.save(invoice);
    }

    private String generateIdentification() {
        return "12345678";
    }

    private Double getBaseRate() {
        return 10.0;
    }

    private Double getAmountPerTime() {
        return 15.0;
    }

    private Double getAmountPerDistance() {
        return 35.0;
    }

    private Double getAmountDiscount() {
        return 3.0;
    }

    private Double getSubtotal() {
        return getBaseRate() + getAmountPerTime() + getAmountPerDistance();
    }

    private Double getTotal() {
        return getSubtotal() - getAmountDiscount();
    }

}
