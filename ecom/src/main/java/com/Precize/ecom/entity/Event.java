package com.Precize.ecom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.Instant;

@Entity
public class Event {
    @Id
    private Long eventId;

    private Instant timestamp;
    private String eventType;

    protected Event() {}

    public Event(Long eventId, Instant timestamp, String eventType) {
        this.eventId = eventId;
        this.timestamp = timestamp;
        this.eventType = eventType;
    }
    @ManyToOne
    @JoinColumn(name = "order_order_id")
    private Order order;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
