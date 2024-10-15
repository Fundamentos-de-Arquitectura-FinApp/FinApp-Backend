package com.example.finappapirest.finances.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float creditLine;

    @OneToOne()
    @JsonIgnore
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    private Store store;
}
