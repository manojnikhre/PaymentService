package com.example.paymentservice.paymentgateway;


import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class RazorpayPaymentGateway implements PaymentGateway{

    private final RazorpayClient razorpayClient;

    public RazorpayPaymentGateway(RazorpayClient razorpayClient){
        this.razorpayClient = razorpayClient;
    }
    @Override
    public String generatePaymentLink(Long orderId, Long amount) throws RazorpayException {
        //Call the Razorpay api to generate the payment link
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amount); //10.00
        paymentLinkRequest.put("currency","INR");
        /*paymentLinkRequest.put("accept_partial",true);*/
        /*paymentLinkRequest.put("first_min_partial_amount",100);*/
        // Get the current instant (epoch timestamp)
        Instant currentInstant = Instant.now();

        // Add 16 minutes to the current instant
        Instant newInstant = currentInstant.plus(16, ChronoUnit.MINUTES);

        // Get the epoch timestamp in milliseconds
        long currentEpochMillis = currentInstant.toEpochMilli();
        long newEpochMillis = newInstant.toEpochMilli();
        paymentLinkRequest.put("expire_by",newEpochMillis);
        paymentLinkRequest.put("reference_id",orderId.toString());
        paymentLinkRequest.put("description","payment for order Id  "+orderId);
        JSONObject customer = new JSONObject();
        customer.put("name","+918149442183");
        customer.put("contact","Manoj Kumar");
        customer.put("email","manoj.nikhre@gmail.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        /*notes.put("policy_name","Jeevan Bima");*/
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://www.scaler.com/academy/mentee-dashboard/todos");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return payment.toString();
    }
}
