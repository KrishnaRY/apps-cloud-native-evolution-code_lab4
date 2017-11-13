package com.example.billing;

import com.example.payments.Gateway;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;

public class BillingMessages {
    @Autowired
    private Gateway gateway;

    @RabbitListener(queues = "paymentQueue")
    public void process(@Payload BillingMessage billingMessage) {
        int amount = billingMessage.getAmount();
        System.out.println(">>> userid " + billingMessage.getUserId());
        System.out.println("Total amount ==>"+billingMessage.getAmount());


        if(gateway.createReocurringPayment(billingMessage.getAmount())){
            System.out.println("Successfully created payment for <" + amount + ">");
        }else{
            System.out.println("Creating payment for <" + amount + "> failed.");
        }


    }
    }

