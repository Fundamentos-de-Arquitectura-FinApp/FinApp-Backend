package com.example.finappapirest.orders.domain.services.commands;

import com.example.finappapirest.orders.domain.model.aggregates.Order;
import com.example.finappapirest.orders.domain.model.commands.CreateOrderCommand;

public interface OrderCommandService {
    Order handle(CreateOrderCommand command);
}
