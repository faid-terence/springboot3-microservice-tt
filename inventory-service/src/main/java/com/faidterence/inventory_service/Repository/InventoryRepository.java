package com.faidterence.inventory_service.Repository;

import com.faidterence.inventory_service.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsBySkuCodeAndQuantityGreaterThanEqual(String skuCode, Integer quantityIsGreaterThan);
}
