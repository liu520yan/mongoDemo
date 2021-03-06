package com.my.mongo.demo;

import static com.mongodb.client.model.Filters.regex;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mongodb.client.result.UpdateResult;
import com.my.mongo.demo.model.Inventory;
import com.my.mongo.demo.repository.InventoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Test
    public void contextLoads() {
        String reservedNode = "stocks." + "MX_Public" + ".reserved";
        String availableNode = "stocks." + "MX_Public" + ".available";
        UpdateResult updateResult = getUpdateResult(reservedNode, availableNode);
        System.out.println(updateResult);
    }

    private UpdateResult getUpdateResult(String reservedNode, String availableNode) {
        Query query = new Query();
        query.addCriteria(where("productCode").is("80WK0104LM"));
//        BasicDBObject object = new BasicDBObject();
//        object.append("$where" ,availableNode+" >= "+ reservedNode);

        query.addCriteria(where("$where").is("this."+availableNode+" >= this."+reservedNode));
        query.addCriteria(where(reservedNode).exists(true));
        Update updateQuery = new Update().inc(reservedNode, -12);

        return inventoryRepository.updateReserveQty("MX_Public","80WK0104LM",2);
    }

}
