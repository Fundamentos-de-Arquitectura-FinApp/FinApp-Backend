package com.example.finappapirest.finances.domain.model.entities;

import com.example.finappapirest.finances.domain.model.valueobjects.PaymentMethod;
import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @OneToOne
    private Quota quota;
}
