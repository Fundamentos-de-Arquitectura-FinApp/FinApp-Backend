package com.example.finappapirest.orders.infraestructure.persistence.jpa.repositories;

import com.example.finappapirest.orders.domain.model.aggregates.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByCredit_Account_Client_Id(Long clientId);
    List<Order> findByCredit_Account_Store_Id(Long storeId);
    Optional<Order> findByCredit_Id(Long creditId);
}
