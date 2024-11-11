package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Car;
import com.example.demo.entity.Reservation;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CarRepository carRepository;
    
    private static final Logger LOGGER=LoggerFactory.getLogger(CarService.class);
    
    //check all reservations
    public List<Reservation> getAllReservations() {
    	LOGGER.info("details of all reservations");
        return reservationRepository.findAll();
    }

    //check reservation by id
    public Optional<Reservation> getReservationById(int id) {
    	LOGGER.info("details of reservations by id");
        return reservationRepository.findById(id);
    }

    //update any reservation
    public Reservation updateReservation(int id, Reservation reservationDetails) {
    	LOGGER.info("update details of reservations ");
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id " + id));
        reservation.setStartDate(reservationDetails.getStartDate());
        reservation.setEndDate(reservationDetails.getEndDate());
        reservation.setCustomerName(reservationDetails.getCustomerName());
        reservation.setTotalPrice(reservationDetails.getTotalPrice());
        return reservationRepository.save(reservation);
    }

    //delete a reservation
    public void deleteReservation(int id) {
    	LOGGER.info("delete details of a reservation");
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id " + id));
        reservationRepository.delete(reservation);
    }
    
    //check availability and book the car 
    public Reservation bookCar(Integer carId, Reservation reservation) {
    	LOGGER.info("booking car ");
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
        if (!car.isAvailable()) throw new RuntimeException("Car not available");

        reservation.setCar(car);
        car.setAvailable(false);
        carRepository.save(car);
        return reservationRepository.save(reservation);
    }
}
