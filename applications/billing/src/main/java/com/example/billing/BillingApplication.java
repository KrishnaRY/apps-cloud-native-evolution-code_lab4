package com.example.billing;

import com.example.payments.Gateway;
import com.example.payments.RecurlyGateway;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class BillingApplication {

	public static void main(String[] args) {

		SpringApplication.run(BillingApplication.class, args);
	}
	@Bean
	 Gateway getGatewayBean(){
		return new RecurlyGateway();
	}

	@Bean
	BillingMessages billingMessages(){
	 	return new  BillingMessages();
	}
@Bean
	MessageListenerAdapter listenerAdapter(BillingMessages billingMessages){
		return new MessageListenerAdapter(billingMessages, "process");
}
	@Bean
	Queue queue() {
		return new Queue("paymentQueue", false);
	}


}
