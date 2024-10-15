package com.example.finappapirest.finances.domain.model.aggregates;

import com.example.finappapirest.finances.domain.model.valueobjects.CreditType;
import jakarta.persistence.*;

@Entity
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float amount;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;
}
