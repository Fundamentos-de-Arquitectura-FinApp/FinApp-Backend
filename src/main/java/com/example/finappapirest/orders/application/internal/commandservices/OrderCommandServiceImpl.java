package com.example.finappapirest.orders.application.internal.commandservices;

import com.example.finappapirest.inventory.interfaces.acl.ProductServiceFacade;
import com.example.finappapirest.orders.domain.model.aggregates.Order;
import com.example.finappapirest.orders.domain.model.commands.CreateOrderCommand;
import com.example.finappapirest.orders.domain.model.commands.PreviewOrderCommand;
import com.example.finappapirest.orders.domain.model.entities.OrderItem;
import com.example.finappapirest.orders.domain.services.commands.OrderCommandService;
import com.example.finappapirest.orders.domain.services.queries.OrderQueryService;
import com.example.finappapirest.orders.infraestructure.persistence.jpa.repositories.OrderRepository;
import com.example.finappapirest.shared.domain.model.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderCommandServiceImpl implements OrderCommandService {
    private final OrderRepository orderRepository;
    private final OrderQueryService orderQueryService;
    private final ProductServiceFacade productServiceFacade;

    @Override
    public Order handle(CreateOrderCommand command) {
        Order order = new Order();
        List<OrderItem> items = new ArrayList<>();
        command.orderItems().forEach(item -> {
            var product = productServiceFacade.getProductById(item.productId());
            if(product == null) {
                throw new BadRequestException("Product with id " + item.productId() + " not found");
            }
            items.add(new OrderItem(product.getId(), item.quantity(), product.getPrice()));
        });
        order.setItems(items);
        this.orderRepository.save(order);
        return order;
    }

    @Override
    public Order handle(PreviewOrderCommand command) {
        Order order = new Order();
        List<OrderItem> items = new ArrayList<>();
        command.orderItems().forEach(item -> {
            var product = productServiceFacade.getProductById(item.productId());
            items.add(new OrderItem(product.getId(), item.quantity(), product.getPrice()));
        });
        order.setItems(items);
        this.orderRepository.save(order);
        return order;
    }

}
