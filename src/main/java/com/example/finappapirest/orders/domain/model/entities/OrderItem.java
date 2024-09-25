package com.example.finappapirest.orders.domain.model.entities;

import com.example.finappapirest.orders.domain.model.aggregates.Order;
import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Integer quantity;
    private Float price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(){}

    public OrderItem(Long productId, Integer integer, Float price) {
        this.productId = productId;
        this.quantity = integer;
        this.price = price;
    }

    public float calculateSubtotal() {
        return this.price * this.quantity;
    }

}
