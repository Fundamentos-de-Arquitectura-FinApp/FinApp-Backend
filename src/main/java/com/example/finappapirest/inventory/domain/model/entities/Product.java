package com.example.finappapirest.inventory.domain.model.entities;

import com.example.finappapirest.finances.domain.model.aggregates.Store;
import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String isActive;
    private Float price;
    @ManyToOne()
    private Store store;
}
