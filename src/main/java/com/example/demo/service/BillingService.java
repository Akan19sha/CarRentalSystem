package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Billing;
import com.example.demo.repository.BillingRepository;

@Service
public class BillingService {
    @Autowired
    private BillingRepository billingRepository;
    private static final Logger LOGGER=LoggerFactory.getLogger(CarService.class);
    
    
    //for making payment
    public Billing processPayment(int reservationId, BigDecimal amount) {
    	LOGGER.info("performing payment");
        Billing billing = new Billing();
        billing.setReservationId(reservationId);
        billing.setAmount(amount);
        billing.setPaymentDate(LocalDate.now());
        return billingRepository.save(billing);
    }
    
    //for getting all billing details
    public List<Billing> getAllBillings() {
    	LOGGER.info("details of all payements");
        return billingRepository.findAll();
    }

    //for getting billing details by id
    public Optional<Billing> getBillingById(int id) {
    	LOGGER.info("details of billings by id");
        return billingRepository.findById(id);
    }
 
    //delete billing details by id
    public void deleteBilling(int id) {
    	LOGGER.info("delete billing details");
        Billing billing = billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Billing not found with id " + id));
        billingRepository.delete(billing);
    }
}
