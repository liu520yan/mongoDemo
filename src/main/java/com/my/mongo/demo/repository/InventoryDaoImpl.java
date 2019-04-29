package com.my.mongo.demo.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mongodb.client.result.UpdateResult;
import com.my.mongo.demo.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class InventoryDaoImpl implements InventoryDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UpdateResult updateReserveQty(String warehouseCode, String productCode , int incQty) {
        String reservedNode = getReservedNode(warehouseCode);
        String availableNode = getAvailableNode(warehouseCode);
        return updateReserveQty(reservedNode, availableNode, productCode, incQty);
    }


    private String getAvailableNode(String warehouseCode){
        return  "stocks." + warehouseCode + ".available";
    }

    private String getReservedNode(String warehouseCode) {
        return "stocks." + warehouseCode + ".reserved";
    }

    private UpdateResult updateReserveQty(String reservedNode, String availableNode, String productCode , int incQty) {
        Query query = new Query();
        query.addCriteria(where("productCode").is(productCode));
        query.addCriteria(where("$where").is("this."+availableNode+" >= this."+reservedNode));
        query.addCriteria(where(reservedNode).exists(true));
        Update updateQuery = new Update().inc(reservedNode, incQty);

        return mongoTemplate.updateFirst(query, updateQuery, Inventory.class);
    }
}
