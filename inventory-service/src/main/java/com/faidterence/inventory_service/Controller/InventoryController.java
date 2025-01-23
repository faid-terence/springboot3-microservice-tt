package com.faidterence.inventory_service.Controller;


import com.faidterence.inventory_service.Dto.InventoryRequest;
import com.faidterence.inventory_service.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private  final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean addInventory(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return inventoryService.inStock(skuCode, quantity);
//        return "Inventory added successfully";
    }
}
