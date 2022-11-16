package com.bpd.challenge.taxi24.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Vehicle {

    private String name;

    private String registration;

    private boolean isEnabled;

    private String brand;

    private String model;

    private String year;

}
