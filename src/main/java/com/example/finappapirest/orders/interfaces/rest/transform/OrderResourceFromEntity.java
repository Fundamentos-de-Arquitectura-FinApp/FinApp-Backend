package com.example.finappapirest.orders.interfaces.rest.transform;

import com.example.finappapirest.inventory.domain.model.entities.Product;
import com.example.finappapirest.inventory.interfaces.acl.ProductServiceFacade;
import com.example.finappapirest.orders.domain.model.aggregates.Order;
import com.example.finappapirest.orders.domain.model.entities.OrderItem;
import com.example.finappapirest.orders.interfaces.rest.resources.response.OrderItemResponse;
import com.example.finappapirest.orders.interfaces.rest.resources.response.OrderResponse;
import com.example.finappapirest.orders.interfaces.rest.resources.response.ProductOrderResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderResourceFromEntity {

    @Autowired
    private ProductServiceFacade productServiceFacade;

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public OrderResponse fromEntity(Order order) {
        List<OrderItemResponse> items = new ArrayList<>();
        for (OrderItem orderItem : order.getItems()) {
            items.add(this.fromEntity(orderItem));
        }

        return new OrderResponse(
                order.getId(),
                items,
                order.calculateTotal(),
                formatter.format(order.getCreatedAt())
        );

    }
    public ProductOrderResponse fromEntity(Product product) {
        return new ProductOrderResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getImageUrl()
        );
    }
    public OrderItemResponse fromEntity(OrderItem orderItem) {
        var product = this.productServiceFacade.getProductById(orderItem.getProductId());
        return new OrderItemResponse(
                this.fromEntity(product),
                orderItem.getPrice(),
                orderItem.getQuantity(),
                orderItem.calculateSubtotal()
        );
    }
}
