ALTER TABLE `t_orders`
    CHANGE `orderNumber` `order_number` VARCHAR(255) DEFAULT NULL,
    CHANGE `skuCode` `sku_code` VARCHAR(255);
