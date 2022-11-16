package com.bpd.challenge.taxi24.models;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.bpd.challenge.taxi24.types.TripStatusType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "trips")
public class Trip {

    private String id;
    private GeoJsonPoint initLocation;
    private GeoJsonPoint endLocation;
    private TripStatusType status;
    private boolean paid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime acceptedByDriverAt;
    private LocalDateTime initAt;
    private LocalDateTime endAt;

    @DocumentReference(lookup = "{'trips':?#{#self._id} }", lazy = true)
    private Passenger passenger;

    @DocumentReference(lookup = "{'trips':?#{#self._id} }", lazy = true)
    private Driver driver;

    @DocumentReference(lookup = "{'trip':?#{#self._id} }", lazy = true)
    private Set<Invoice> invoices;

}
