package com.my.mongo.demo.model;

import java.io.Serializable;
import java.util.Map;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Inventories")
public class Inventory implements Serializable {

	private static final long serialVersionUID = 1L;

	private String productCode;
	private Map<String,Stock> stocks;
	private Map<String, Integer> leadTimes;


}
