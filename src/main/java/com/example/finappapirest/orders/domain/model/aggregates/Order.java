package com.example.finappapirest.orders.domain.model.aggregates;

import com.example.finappapirest.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_order")
public class Order extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
