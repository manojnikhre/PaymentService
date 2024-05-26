package com.example.paymentservice.paymentgateway;

import com.razorpay.RazorpayException;

public interface PaymentGateway {
    String generatePaymentLink(Long orderId, Long amount) throws  RazorpayException;
}
