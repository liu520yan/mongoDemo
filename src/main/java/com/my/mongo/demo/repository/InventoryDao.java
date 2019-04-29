package com.my.mongo.demo.repository;

import com.mongodb.client.result.UpdateResult;

public interface InventoryDao {

    UpdateResult updateReserveQty(String warehouseCode, String productCode , int incQty);

}
