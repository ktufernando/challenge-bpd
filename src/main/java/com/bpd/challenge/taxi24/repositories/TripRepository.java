package com.bpd.challenge.taxi24.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bpd.challenge.taxi24.models.Trip;
import com.bpd.challenge.taxi24.types.TripStatusType;

@Repository
public interface TripRepository extends MongoRepository<Trip, String> {

    List<Trip> findByStatus(TripStatusType status);

}
