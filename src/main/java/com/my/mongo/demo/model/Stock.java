package com.my.mongo.demo.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Stock implements Serializable {

    private static final long serialVersionUID = 11235324526L;

    private int available;
    private int reserved;
    private int overselling;
    private boolean realtime;
    private boolean byPassCheck;
    private String inStockStatus;
}
