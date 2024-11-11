package com.example.demo.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Billing;
import com.example.demo.service.BillingService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/billing")
public class BillingController {
	@Autowired
    private BillingService billingService;
	
	
    //make payment
    @PostMapping("/pay")
    public Billing processPayment(@RequestBody Billing billing) {
        return billingService.processPayment(billing.getReservationId(), billing.getAmount());
    }
    
    //get all booking details
    @GetMapping
    public List<Billing> getAllBillings() {
        return billingService.getAllBillings();
    }

  //get booking details by id
    @GetMapping("/{id}")
    public Optional<Billing> getBillingById(@PathVariable int id) {
        return billingService.getBillingById(id);
    }

    //delete billing details by id 
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBilling(@PathVariable int id) {
        billingService.deleteBilling(id);
    }
}
