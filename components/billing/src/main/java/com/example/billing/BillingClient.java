package com.example.billing;



import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BillingClient {

    private String billingServiceEndPoint;

    private RestTemplate restTemplate;


    public BillingClient(RestTemplate restTemplate, String billingServiceEndPoint){
        this.billingServiceEndPoint=billingServiceEndPoint;
        this.restTemplate=restTemplate;
    }

    public void run(String userId,int amount){

        String path="//"+billingServiceEndPoint+"/reccurringPayment";
        System.out.println("path===>"+path);
        ResponseEntity<String> response = restTemplate.postForEntity(path, amount , String.class );
        System.out.println("response===>"+response);

    }


}
