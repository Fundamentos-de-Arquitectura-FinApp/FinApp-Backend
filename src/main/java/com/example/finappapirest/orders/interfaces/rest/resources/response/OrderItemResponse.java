package com.example.finappapirest.orders.interfaces.rest.resources.response;

public record OrderItemResponse(
        ProductOrderResponse product,
        Float price,
        Integer quantity,
        Float subtotal
) {
}
