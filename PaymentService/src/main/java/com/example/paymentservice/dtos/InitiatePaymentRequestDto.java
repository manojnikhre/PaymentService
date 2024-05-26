package com.example.paymentservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InitiatePaymentRequestDto {

    private Long orderId;
    private Long amount;
}
