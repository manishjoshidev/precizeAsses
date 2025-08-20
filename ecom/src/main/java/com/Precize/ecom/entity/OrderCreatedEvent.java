package com.Precize.ecom.entity;

import java.util.Map;

public class OrderCreatedEvent extends Event {
    public Long orderId;
    public String customerId;
    public Map<String,Integer> items;
    public double totalAmount;
}
