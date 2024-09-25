package com.example.finappapirest.orders.infraestructure.persistence.jpa.repositories;

import com.example.finappapirest.orders.domain.model.aggregates.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
