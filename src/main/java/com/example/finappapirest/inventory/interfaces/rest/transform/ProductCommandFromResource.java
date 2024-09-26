package com.example.finappapirest.inventory.interfaces.rest.transform;

import com.example.finappapirest.inventory.domain.model.commands.CreateProductCommand;
import com.example.finappapirest.inventory.domain.model.commands.DeleteProductCommand;
import com.example.finappapirest.inventory.domain.model.commands.UpdateProductCommand;
import com.example.finappapirest.inventory.interfaces.rest.resources.request.CreateProductRequest;
import com.example.finappapirest.inventory.interfaces.rest.resources.request.UpdateProductRequest;

public class ProductCommandFromResource {
    public static CreateProductCommand commandFromResource(CreateProductRequest request, Long storeId){
        return new CreateProductCommand(request.name(),request.description(),request.price(),request.imageUrl(),storeId);
    }

    public static UpdateProductCommand commandFromResource(UpdateProductRequest request, Long storeId, Long productId){
        return new UpdateProductCommand(productId,request.name(),request.description(),request.price(),request.imageUrl(),storeId);
    }

    public static DeleteProductCommand commandFromResource(Long productId,Long storeId){
        return new DeleteProductCommand(productId,storeId);
    }
}
