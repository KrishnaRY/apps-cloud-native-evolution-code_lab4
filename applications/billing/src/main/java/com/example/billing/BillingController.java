package com.example.billing;

import com.example.payments.Gateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;



@RestController
public class BillingController {


@Autowired
private Gateway gateway;

    @RequestMapping(value = "/reccurringPayment", method = RequestMethod.POST)
    public ResponseEntity<String> reoccurringPayment( @RequestBody int amount) {
        System.out.println("Total amount ==>"+amount);
        ResponseEntity response;

        if(gateway.createReocurringPayment(amount)){
            response= new ResponseEntity("{errors: []}", HttpStatus.CREATED);
        }else{
            response= new ResponseEntity("{errors: [error1,error2,error3]}", HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}