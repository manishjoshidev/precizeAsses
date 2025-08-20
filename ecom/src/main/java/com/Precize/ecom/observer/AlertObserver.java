package com.Precize.ecom.observer;

import com.Precize.ecom.entity.Event;
import com.Precize.ecom.entity.Order;
import com.Precize.ecom.entity.OrderStatus;

public class AlertObserver implements Observer {

    @Override
    public void update(Order order, Event event) {
        if (order.getStatus() == OrderStatus.CANCELLED) {
            System.out.println("[ALERT] Order " + order.getOrderId() + " was cancelled!");
        }
    }
}
