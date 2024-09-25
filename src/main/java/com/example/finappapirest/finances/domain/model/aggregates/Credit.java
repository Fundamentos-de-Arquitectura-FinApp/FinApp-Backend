package com.example.finappapirest.finances.domain.model.aggregates;

import com.example.finappapirest.finances.domain.model.valueobjects.CreditType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float amount;

}
