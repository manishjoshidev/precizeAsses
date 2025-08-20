package com.Precize.ecom.observer;

import com.Precize.ecom.entity.Event;
import com.Precize.ecom.entity.Order;

public interface Observer {
    void update(Order order, Event event);
}
