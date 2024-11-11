package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;


@Service
public class CarService {
	 @Autowired
	    private CarRepository carRepository;

	private static final Logger LOGGER=LoggerFactory.getLogger(CarService.class);
	 
	    //check the cars that are available 
	    public List<Car> getAvailableCars() {
	    	LOGGER.info("details of cars that are available");
	        return carRepository.findByAvailable(true);
	    }
	    
	  //get details of all cars
	    public List<Car> getAllCars() {
	    	LOGGER.info("details of all cars");
	        return carRepository.findAll();
	    }
	    
	  //get car details by car id 
	    public Optional<Car> getCarById(Integer id) {
	    	LOGGER.info("details of all cars by id ");
	        return carRepository.findById(id);
	    }
	    
	  //create a new car detail
	    public void createCar(Car car) {
	    	LOGGER.info("adding new car ");
	    	carRepository.save(car);
	    }
	    
	  //delete car by id 
	    public void deleteCar(Integer id) {
	    	LOGGER.info("deleting a car details");
	        Optional<Car> car = carRepository.findById(id);
	        car.ifPresent(carRepository::delete);
	    }
}
