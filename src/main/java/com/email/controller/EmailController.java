package com.email.controller;

import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import com.email.service.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @CrossOrigin
public class EmailController {
    @Autowired
    private EmailServices emailServices;
    @RequestMapping("/welcome")
    public  String welcome(){
        return "This is my Email API";
    }
    @RequestMapping(value = "/sendemail", method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){

        System.out.println(request);
        boolean result = this.emailServices.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
        if (result) {
            return ResponseEntity.ok("Email Sent Successfully..");
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent..");
        }
    }
}
