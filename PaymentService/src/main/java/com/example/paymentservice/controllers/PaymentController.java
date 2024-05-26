package com.example.paymentservice.controllers;


import com.example.paymentservice.dtos.InitiatePaymentRequestDto;
import com.example.paymentservice.services.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    PaymentService paymentService;
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }


    @PostMapping("/")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto initiatePaymentRequestDto){
        try {
            return paymentService.initiatePayment(
                    initiatePaymentRequestDto.getOrderId(),
                    initiatePaymentRequestDto.getAmount());
        } catch (RazorpayException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
