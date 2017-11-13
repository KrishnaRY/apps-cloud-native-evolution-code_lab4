package com.example.billing;



import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;


public class RabbitBillingClient implements BillingClient {

private org.springframework.amqp.core.Queue queueName ;

private RabbitTemplate rabbitTemplate;
@Autowired
    public RabbitBillingClient(RabbitTemplate rabbitTemplate){
//        this.queueName=queueName;
        this.rabbitTemplate=rabbitTemplate;

    }

    @Override
    public void run(String userId, int amount) {
        rabbitTemplate.convertAndSend("paymentQueue", new BillingMessage(userId, amount));
    }
}
