package com.example.finappapirest.finances.interfaces.rest;

import com.example.finappapirest.finances.domain.model.queries.store.GetAllStoresQuery;
import com.example.finappapirest.finances.domain.model.queries.store.GetStoreByIdQuery;
import com.example.finappapirest.finances.domain.model.queries.store.GetStoreByUserIdQuery;
import com.example.finappapirest.finances.domain.services.commands.StoreCommandService;
import com.example.finappapirest.finances.domain.services.queries.StoreQueryService;
import com.example.finappapirest.finances.interfaces.rest.resources.request.CreateStoreRequest;
import com.example.finappapirest.finances.interfaces.rest.resources.request.UpdateStoreRequest;
import com.example.finappapirest.finances.interfaces.rest.resources.response.StoreResponse;
import com.example.finappapirest.finances.interfaces.rest.transform.StoreCommandFromResource;
import com.example.finappapirest.finances.interfaces.rest.transform.StoreResourceFromEntity;
import com.example.finappapirest.shared.interfaces.utils.UserUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/stores")
@Tag(name = "Stores", description = "Stores management")
@AllArgsConstructor
public class StoreController {
    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @GetMapping()
    @Operation(summary = "Get all stores", description = "Get all stores in the system")
    public ResponseEntity<List<StoreResponse>> getStores() {
        var query = new GetAllStoresQuery();
        var stores = storeQueryService.handle(query);
        return ResponseEntity.ok(stores.stream().map(StoreResourceFromEntity::entityToResponse).toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get store by id", description = "Get store by id in the system")
    public ResponseEntity<StoreResponse> getStore(@PathVariable Long id) {
        var query = new GetStoreByIdQuery(id);
        var store = storeQueryService.handle(query);
        return ResponseEntity.ok(StoreResourceFromEntity.entityToResponse(store));
    }

    @GetMapping("/user")
    @Operation(summary = "Get store by user", description = "Get store by user actual in the system")
    public ResponseEntity<StoreResponse> getStoreByUser() {
        var userId = UserUtils.getCurrentUserId();
        var query = new GetStoreByUserIdQuery(userId);
        return ResponseEntity.ok(StoreResourceFromEntity.entityToResponse(storeQueryService.handle(query)));
    }

    @PostMapping()
    @Operation(summary = "Create store", description = "Create store in the system")
    public ResponseEntity<StoreResponse> createStore(@RequestBody CreateStoreRequest request) {
        var command = StoreCommandFromResource.fromResource(request);
        var store = storeCommandService.handle(command);
        return new ResponseEntity<>(StoreResourceFromEntity.entityToResponse(store), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update store", description = "Update store in the system")
    public ResponseEntity<StoreResponse> updateStore(@PathVariable Long id, @RequestBody UpdateStoreRequest request) {
        var command = StoreCommandFromResource.fromResource(request);
        var store = storeCommandService.handle(command);
        return ResponseEntity.ok(StoreResourceFromEntity.entityToResponse(store));
    }

}
