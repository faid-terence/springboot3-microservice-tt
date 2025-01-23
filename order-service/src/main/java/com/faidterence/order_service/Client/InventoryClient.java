package com.faidterence.order_service.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", url = "http://localhost:8082")
public interface InventoryClient {


    @RequestMapping(method = RequestMethod.GET, value = "/api/inventory")
    boolean inStock(@RequestParam String skuCode, @RequestParam Integer quantity);
}
