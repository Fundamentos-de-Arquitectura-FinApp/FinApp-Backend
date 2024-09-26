package com.example.finappapirest.inventory.interfaces.rest.transform;

import com.example.finappapirest.inventory.domain.model.entities.Product;
import com.example.finappapirest.inventory.interfaces.rest.resources.response.ProductResponse;

public class ProductResourceFromEntity {
    public static ProductResponse resourceFromEntity(Product product){
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImageUrl());
    }
}
