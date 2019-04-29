package com.my.mongo.demo.repository;

import com.my.mongo.demo.model.Inventory;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory, Long> ,InventoryDao{
	Inventory findByProductCode(String productCode);

	List<Inventory> findByProductCodeIn(List<String> productCodes);
}
