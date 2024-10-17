package com.example.finappapirest.finances.domain.model.entities;

import com.example.finappapirest.finances.domain.model.aggregates.Credit;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payment_plan")
public class PaymentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Quota> quotas = new ArrayList<>();

    public void createQuotas(Credit credit) {

    }
}
