package com.example.finappapirest.orders.domain.model.commands;

import com.example.finappapirest.orders.interfaces.rest.resources.request.OrderItemRequest;

import java.util.List;

public record PreviewOrderCommand(
        List<OrderItemRequest> orderItems
) {
}
