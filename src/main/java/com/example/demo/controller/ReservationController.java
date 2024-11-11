package com.example.demo.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Reservation;
import com.example.demo.service.ReservationService;

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
@RequestMapping("/reservations")
public class ReservationController {
	 @Autowired
    private ReservationService reservationService;

	 //for booking the car 
    @PostMapping("/book/{carId}")
    public Reservation bookCar(@PathVariable Integer carId, @RequestBody Reservation reservation) {
        return reservationService.bookCar(carId, reservation);
    }
    
    
   //check all reservations
    @GetMapping("/allbookings")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    //check reservation by id
    @GetMapping("/bookings/{id}")
    public Optional<Reservation> getReservationById(@PathVariable int id) {
        return reservationService.getReservationById(id);
    }

    //update reservation
    @PutMapping("/updatebooking/{id}")
    public Reservation updateReservation(@PathVariable int id, @RequestBody Reservation reservationDetails) {
        return reservationService.updateReservation(id, reservationDetails);
    }

    
    //update reservation
    @DeleteMapping("/deletebooking/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
    }
    
}
