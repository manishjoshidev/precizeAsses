package com.Precize.ecom.service;

import com.Precize.ecom.entity.*;
import com.Precize.ecom.observer.Observer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class EventProcessor {

    private final Map<Long, Order> orderStore = new HashMap<>();
    private final List<Observer> observers = new CopyOnWriteArrayList<>();


    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers(Order order, Event event) {
        for (Observer obs : observers) {
            obs.update(order, event);
        }
    }

    public void processEvent(Event event) {
        if (event instanceof OrderCreatedEvent e) {
            Order order = new Order(e.orderId, e.customerId, e.items, e.totalAmount);
            order.getEventHistory().add(event);
            orderStore.put(order.getOrderId(), order);
            notifyObservers(order, event);

        } else if (event instanceof PaymentReceivedEvent e) {
            Order order = orderStore.get(e.orderId);
            if (order != null) {
                if (e.amountPaid >= order.getTotalAmount()) {
                    order.setStatus(OrderStatus.PAID);
                } else {
                    order.setStatus(OrderStatus.PARTIALLY_PAID);
                }
                order.getEventHistory().add(event);
                notifyObservers(order, event);
            }

        } else if (event instanceof ShippingScheduledEvent e) {
            Order order = orderStore.get(e.orderId);
            if (order != null) {
                order.setStatus(OrderStatus.SHIPPED);
                order.getEventHistory().add(event);
                notifyObservers(order, event);
            }

        } else if (event instanceof OrderCancelledEvent e) {
            Order order = orderStore.get(e.orderId);
            if (order != null) {
                order.setStatus(OrderStatus.CANCELLED);
                order.getEventHistory().add(event);
                notifyObservers(order, event);
            }

        } else {
            System.out.println("[WARN] Unsupported event type: " + event.getEventType());
        }
    }
}
