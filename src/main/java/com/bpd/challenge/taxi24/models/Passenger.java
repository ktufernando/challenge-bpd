package com.bpd.challenge.taxi24.models;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.bpd.challenge.taxi24.types.PassengerStatusType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "passengers")
public class Passenger {

    private String id;
    private String name;
    private PassengerStatusType status;

    @JsonIgnore
    @DocumentReference(lazy = true)
    private Set<Trip> trips;

}
