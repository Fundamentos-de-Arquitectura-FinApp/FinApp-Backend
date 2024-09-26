package com.example.finappapirest.finances.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/stores")
@Tag(name = "Stores", description = "Stores management")
public class StoreController {

    @GetMapping()
    @Operation(summary = "Get all stores", description = "Get all stores in the system")
    public String getStores() {
        return "Stores";
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get store by id", description = "Get store by id in the system")
    public String getStore(@PathVariable Long id) {
        return "Store " + id;
    }

    @PostMapping()
    @Operation(summary = "Create store", description = "Create store in the system")
    public String createStore() {
        return "Store created";
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update store", description = "Update store in the system")
    public String updateStore(@PathVariable Long id) {
        return "Store updated";
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete store", description = "Delete store in the system")
    public String deleteStore(@PathVariable Long id) {
        return "Store deleted";
    }
}
