package com.faidterence.inventory_service.Service;


import com.faidterence.inventory_service.Dto.InventoryRequest;
import com.faidterence.inventory_service.Model.Inventory;
import com.faidterence.inventory_service.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {


    private  final InventoryRepository inventoryRepository;

    public boolean inStock( String skuCode , Integer quantity) {


        return inventoryRepository.existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);

    }
}
