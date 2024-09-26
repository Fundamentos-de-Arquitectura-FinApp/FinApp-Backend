package com.example.finappapirest.inventory.interfaces.rest;

import com.example.finappapirest.inventory.domain.model.commands.UpdateProductCommand;
import com.example.finappapirest.inventory.domain.model.entities.Product;
import com.example.finappapirest.inventory.domain.model.queries.GetAllProductsByStoreQuery;
import com.example.finappapirest.inventory.domain.services.commands.ProductsCommandService;
import com.example.finappapirest.inventory.domain.services.queries.ProductsQueryService;
import com.example.finappapirest.inventory.interfaces.rest.resources.request.CreateProductRequest;
import com.example.finappapirest.inventory.interfaces.rest.resources.request.UpdateProductRequest;
import com.example.finappapirest.inventory.interfaces.rest.resources.response.ProductResponse;
import com.example.finappapirest.inventory.interfaces.rest.transform.ProductCommandFromResource;
import com.example.finappapirest.inventory.interfaces.rest.transform.ProductResourceFromEntity;
import com.example.finappapirest.shared.interfaces.utils.UserUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
@Tag(name = "Products", description = "Products Management Endpoints")
public class ProductsController {
    private final ProductsCommandService productsCommandService;
    private final ProductsQueryService productsQueryService;

    @Secured({"ROLE_STORE"})
    @GetMapping("/store")
    @Operation(summary = "Get all products by store")
    public ResponseEntity<List<ProductResponse>> getProductsByStore(){
        Long storeId = UserUtils.getCurrentUserId();
        List<Product> products  = productsQueryService.handle(new GetAllProductsByStoreQuery(storeId));
        List<ProductResponse> productResponses = products.stream().map(ProductResourceFromEntity::resourceFromEntity).toList();
        return ResponseEntity.ok(productResponses);
    }
    @PostMapping("/store")
    @Operation(summary = "Create a product")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request){
        Long storeId = UserUtils.getCurrentUserId();
        var command = ProductCommandFromResource.commandFromResource(request, storeId);
        Product product = productsCommandService.handle(command);
        return new ResponseEntity<>(ProductResourceFromEntity.resourceFromEntity(product), HttpStatus.CREATED);
    }

    @PutMapping("/store/{productId}")
    @Operation(summary = "Update a product")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductRequest request){
        Long storeId = UserUtils.getCurrentUserId();
        UpdateProductCommand command = ProductCommandFromResource.commandFromResource(request, storeId, productId);
        Product product = productsCommandService.handle(command);
        return new ResponseEntity<>(ProductResourceFromEntity.resourceFromEntity(product), HttpStatus.OK);
    }

    @DeleteMapping("/store/{productId}")
    @Operation(summary = "Delete a product")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        Long storeId = UserUtils.getCurrentUserId();
        productsCommandService.handle(ProductCommandFromResource.commandFromResource(productId, storeId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
