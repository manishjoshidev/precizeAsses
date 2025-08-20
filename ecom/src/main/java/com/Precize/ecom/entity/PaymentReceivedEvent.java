package com.Precize.ecom.entity;

public class PaymentReceivedEvent extends Event {
    public Long orderId;
    public double amountPaid;
}
