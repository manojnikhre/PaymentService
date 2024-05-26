package com.example.paymentservice.services;


import com.example.paymentservice.paymentgateway.RazorpayPaymentGateway;
import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private RazorpayPaymentGateway razorpayPaymentGateway;

    public PaymentService(RazorpayPaymentGateway razorpayPaymentGateway){
        this.razorpayPaymentGateway = razorpayPaymentGateway;
    }

    public String initiatePayment(Long orderId, Long amount) throws RazorpayException {

        //Make a call to Payment Gateway to generate the payment line.
        return razorpayPaymentGateway.generatePaymentLink(orderId,amount);
    }
}
