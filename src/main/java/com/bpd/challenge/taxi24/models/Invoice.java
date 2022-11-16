package com.bpd.challenge.taxi24.models;

import java.time.LocalDateTime;
import java.util.Calendar;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "invoices")
public class Invoice {

    private String id;

    private String identification;

    private Double baseRate;

    private Double time;

    private Double distance;

    private Double subtotal;

    private Double discount;

    private Double total;

    private LocalDateTime datetime;

    @DocumentReference(lookup = "{'invoices':?#{#self._id} }", lazy = true)
    private Trip trip;

}
