package com.example.finappapirest.orders.domain.services.queries;

import com.example.finappapirest.orders.domain.model.aggregates.Order;
import com.example.finappapirest.orders.domain.model.queries.*;

import java.util.List;

public interface OrderQueryService {
    List<Order> handle(GetAllOrdersQuery query);
    Order handle(GetOrderByIdQuery query);
    List<Order> handle(GetOrderByClientIdQuery query);
    List<Order> handle(GetOrdersByStoreQuery query);
    Order handle(GetOrderByCreditIdQuery query);
}
