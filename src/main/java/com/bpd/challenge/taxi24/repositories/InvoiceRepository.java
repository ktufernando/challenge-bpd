package com.bpd.challenge.taxi24.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bpd.challenge.taxi24.models.Invoice;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, String> {

}
