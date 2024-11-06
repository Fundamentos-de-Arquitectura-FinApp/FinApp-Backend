package com.example.finappapirest.orders.interfaces.rest;

import com.example.finappapirest.orders.application.internal.queryservices.OrderQueryServiceImpl;
import com.example.finappapirest.orders.domain.model.aggregates.Order;
import com.example.finappapirest.orders.domain.model.commands.PreviewOrderCommand;
import com.example.finappapirest.orders.domain.model.queries.GetAllOrdersQuery;
import com.example.finappapirest.orders.domain.model.queries.GetOrderByClientIdQuery;
import com.example.finappapirest.orders.domain.model.queries.GetOrderByIdQuery;
import com.example.finappapirest.orders.domain.model.queries.GetOrdersByStoreQuery;
import com.example.finappapirest.orders.domain.services.commands.OrderCommandService;
import com.example.finappapirest.orders.domain.services.queries.OrderQueryService;
import com.example.finappapirest.orders.interfaces.rest.resources.request.CreateOrderRequest;
import com.example.finappapirest.orders.interfaces.rest.resources.response.OrderResponse;
import com.example.finappapirest.orders.interfaces.rest.transform.OrderCommandFromResource;
import com.example.finappapirest.orders.interfaces.rest.transform.OrderResourceFromEntity;
import com.example.finappapirest.shared.interfaces.utils.UserUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
@Tag(name = "Orders", description = "Orders management")
public class OrdersController {

    private final OrderQueryService orderQueryService;
    private final OrderCommandService orderCommandService;
    private final OrderResourceFromEntity orderResourceFromEntity;

    @GetMapping()
    @Operation(summary = "Get all orders", description = "Get all orders")
    public ResponseEntity<List<OrderResponse>> getOrders() {
        GetAllOrdersQuery query = new GetAllOrdersQuery();
        List<Order> orders = orderQueryService.handle(query);
        List<OrderResponse> response = orders.stream().map(orderResourceFromEntity::fromEntity).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/store")
    @Operation(summary = "Get orders by store", description = "Get orders by store")
    public ResponseEntity<List<OrderResponse>> getOrdersByStore() {
        Long storeId = UserUtils.getCurrentUserId();
        GetOrdersByStoreQuery query = new GetOrdersByStoreQuery(storeId);
        List<Order> orders = orderQueryService.handle(query);
        List<OrderResponse> response = orders.stream().map(orderResourceFromEntity::fromEntity).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Get order", description = "Get order by id")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId) {
        GetOrderByIdQuery query = new GetOrderByIdQuery(orderId);
        Order order = orderQueryService.handle(query);
        OrderResponse response = orderResourceFromEntity.fromEntity(order);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get orders by client", description = "Get orders by client")
    public ResponseEntity<List<OrderResponse>> getOrdersByClient(@PathVariable Long clientId) {
        GetOrderByClientIdQuery query = new GetOrderByClientIdQuery(clientId);
        List<Order> orders = orderQueryService.handle(query);
        List<OrderResponse> response = orders.stream().map(orderResourceFromEntity::fromEntity).toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/preview")
    @Operation(summary = "Preview order", description = "Preview order before creating it")
    public ResponseEntity<OrderResponse> previewOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        System.out.println(createOrderRequest);
        PreviewOrderCommand command = OrderCommandFromResource.fromResource(createOrderRequest);
        System.out.println(command);
        Order order = orderCommandService.handle(command);
        OrderResponse response = orderResourceFromEntity.fromEntity(order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping()
    @Operation(summary = "Create order", description = "Create order in the system and return the order id")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        PreviewOrderCommand command = OrderCommandFromResource.fromResource(createOrderRequest);
        Order order = orderCommandService.handle(command);
        OrderResponse response = orderResourceFromEntity.fromEntity(order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
