package com.faidterence.inventory_service.Dto;

public record InventoryRequest(
        Long id,
        String skuCode,
        Integer quantity
) {
}
