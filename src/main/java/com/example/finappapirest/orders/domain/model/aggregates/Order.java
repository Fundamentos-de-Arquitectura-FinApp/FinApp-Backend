package com.example.finappapirest.orders.domain.model.aggregates;

import com.example.finappapirest.finances.domain.model.aggregates.Credit;
import com.example.finappapirest.orders.domain.model.entities.OrderItem;
import com.example.finappapirest.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "customer_order")
public class Order extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Credit credit;

    public Double calculateTotal() {
        return this.items.stream().mapToDouble(OrderItem::calculateSubtotal).sum();
    }
}
