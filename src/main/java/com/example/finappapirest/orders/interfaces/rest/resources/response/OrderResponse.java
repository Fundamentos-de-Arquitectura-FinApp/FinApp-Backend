package com.example.finappapirest.orders.interfaces.rest.resources.response;

import java.util.Date;
import java.util.List;

public record OrderResponse(
        Long id,
        List<OrderItemResponse> items,
        Double total,
        String createdAt
) {
}
