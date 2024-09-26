package com.example.finappapirest.finances.domain.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_plan")
public class PaymentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
