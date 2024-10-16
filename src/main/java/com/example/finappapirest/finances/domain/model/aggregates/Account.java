package com.example.finappapirest.finances.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne(fetch = FetchType.EAGER)
    private Store store;

    public Account() {}
    public Account(Float creditLine, Client client, Store store) {
        this.creditLine = creditLine;
        this.client = client;
        this.store = store;
    }
}
