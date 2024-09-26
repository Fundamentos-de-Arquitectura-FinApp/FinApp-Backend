package com.example.finappapirest.orders.domain.model.commands;

import java.util.List;

public record CreateOrderCommand(List<Long> productIds, Long creditId) {

}
