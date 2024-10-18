package com.example.finappapirest.orders.application.internal.queryservices;

import com.example.finappapirest.orders.domain.model.aggregates.Order;
import com.example.finappapirest.orders.domain.model.queries.*;
import com.example.finappapirest.orders.domain.services.queries.OrderQueryService;
import com.example.finappapirest.orders.infraestructure.persistence.jpa.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService {

    public OrderRepository orderRepository;

    @Override
    public List<Order> handle(GetAllOrdersQuery query) {
        return orderRepository.findAll();
    }

    @Override
    public Order handle(GetOrderByIdQuery query) {
        return orderRepository.findById(query.orderId()).orElse(null);
    }

    @Override
    public List<Order> handle(GetOrderByClientIdQuery query) {
        return orderRepository.findByCredit_Account_Client_Id(query.clientId());
    }

    @Override
    public List<Order> handle(GetOrdersByStoreQuery query) {
        return orderRepository.findByCredit_Account_Store_Id(query.storeId());
    }

    @Override
    public Order handle(GetOrderByCreditIdQuery query) {
        return orderRepository.findByCredit_Id(query.creditId()).orElse(null);
    }
}
