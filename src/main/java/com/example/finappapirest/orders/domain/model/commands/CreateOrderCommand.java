package com.example.finappapirest.orders.domain.model.commands;

import com.example.finappapirest.orders.interfaces.rest.resources.request.OrderItemRequest;
import org.antlr.v4.runtime.misc.Pair;

import java.util.List;

public record CreateOrderCommand(
        List<OrderItemRequest> orderItems,
        Long creditId
) {
}
