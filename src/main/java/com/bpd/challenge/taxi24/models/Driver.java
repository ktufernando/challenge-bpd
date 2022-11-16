package com.bpd.challenge.taxi24.models;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.bpd.challenge.taxi24.types.DriverStatusType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "drivers")
public class Driver {

    private String id;
    private String name;
    private GeoJsonPoint location;
    private DriverStatusType status;
    private Vehicle vehicle;

    @JsonIgnore
    @DocumentReference(lazy = true)
    private Set<Trip> trips;
}
