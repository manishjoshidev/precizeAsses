package com.Precize.ecom.observer;

import com.Precize.ecom.entity.Event;
import com.Precize.ecom.entity.Order;

public class LoggerObserver implements Observer {

    @Override
    public void update(Order order, Event event) {
        System.out.println("[LOG] Event " + event.getEventType() +
                " applied to Order " + order.getOrderId());
    }
}
