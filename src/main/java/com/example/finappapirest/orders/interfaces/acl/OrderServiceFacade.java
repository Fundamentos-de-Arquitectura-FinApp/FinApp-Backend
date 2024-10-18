package com.example.finappapirest.orders.interfaces.acl;

import com.example.finappapirest.orders.domain.model.aggregates.Order;
import com.example.finappapirest.orders.domain.model.queries.GetOrderByIdQuery;
import com.example.finappapirest.orders.domain.services.queries.OrderQueryService;
import com.example.finappapirest.shared.domain.model.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceFacade {
    private final OrderQueryService orderQueryService;

    public Double getAmountByOrderId(Long orderId) {
        GetOrderByIdQuery query = new GetOrderByIdQuery(orderId);
        Order order = orderQueryService.handle(query);
        if(order == null) {
            throw new BadRequestException("Order not found");
        }
        return  order.calculateTotal();
    }
}
