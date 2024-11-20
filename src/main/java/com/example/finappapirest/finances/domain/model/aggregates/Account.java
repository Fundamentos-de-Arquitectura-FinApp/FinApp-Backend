package com.example.finappapirest.finances.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float creditLine;

    @OneToOne()
    @JsonIgnore
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Credit> credits = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Store store;

    public Account() {}
    public Account(Float creditLine, Client client, Store store) {
        this.creditLine = creditLine;
        this.client = client;
        this.store = store;
    }
    public void addCredit(Credit credit) {
        this.credits.add(credit);
    }
}
