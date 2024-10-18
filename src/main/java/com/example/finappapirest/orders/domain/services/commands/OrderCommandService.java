package com.example.finappapirest.orders.domain.services.commands;

import com.example.finappapirest.orders.domain.model.aggregates.Order;
import com.example.finappapirest.orders.domain.model.commands.CreateOrderCommand;
import com.example.finappapirest.orders.domain.model.commands.PreviewOrderCommand;

public interface OrderCommandService {
    Order handle(CreateOrderCommand command);
    Order handle(PreviewOrderCommand command);
}
