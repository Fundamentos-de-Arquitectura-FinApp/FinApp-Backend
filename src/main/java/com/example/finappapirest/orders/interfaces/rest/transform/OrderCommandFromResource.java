package com.example.finappapirest.orders.interfaces.rest.transform;

import com.example.finappapirest.orders.domain.model.commands.PreviewOrderCommand;
import com.example.finappapirest.orders.interfaces.rest.resources.request.CreateOrderRequest;
import com.example.finappapirest.orders.interfaces.rest.resources.request.OrderItemRequest;

import java.util.List;

public class OrderCommandFromResource {
    public static PreviewOrderCommand fromResource(CreateOrderRequest createOrderRequest) {
        return new PreviewOrderCommand(
                createOrderRequest.items()
        );
    }

}
