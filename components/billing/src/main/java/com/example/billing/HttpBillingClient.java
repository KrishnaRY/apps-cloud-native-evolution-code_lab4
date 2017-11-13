package com.example.billing;



import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpBillingClient  implements BillingClient{

    private String billingServiceEndPoint;

    private RestTemplate restTemplate;


    public HttpBillingClient(RestTemplate restTemplate, String billingServiceEndPoint){
        this.billingServiceEndPoint=billingServiceEndPoint;
        this.restTemplate=restTemplate;
    }
    @Override
    @HystrixCommand(fallbackMethod = "runFallback")
    public void run(String userId,int amount){

        String path="//"+billingServiceEndPoint+"/reccurringPayment";
        System.out.println("path===>"+path);
        ResponseEntity<String> response = restTemplate.postForEntity(path, amount , String.class );
        System.out.println("response===>"+response);

    }

    public void runFallback(String userId,int amount){
        System.out.println(" Executing fallback method for user ===>["+userId+"] and amount ===>["+amount+"]");

    }


}
