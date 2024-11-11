package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.CarService;

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
@RequestMapping("/cars")
public class CarController {
	@Autowired
    private CarService carService;
	CarRepository repo;

	//check the cars that are available
    @GetMapping("/available")
    public List<Car> getAvailableCars() {
        return carService.getAvailableCars();
    }
  
    //get details of all cars
    @GetMapping("/allcars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }
    
    //get car details by car id 
    @GetMapping("/allcars/{id}")
    public Car getCarById(@PathVariable Integer id) {
        return carService.getCarById(id)
                         .orElseThrow(() -> new RuntimeException("Car not found with ID " + id));
    }
    
   //create a new car detail
    @PostMapping("/car/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCar(@RequestBody Car car) {
        carService.createCar(car);
    }
    
    //delete car by id 
    @DeleteMapping("/car/delete/{id}")
    public void deletecar(@PathVariable Integer id)
    {
    	carService.deleteCar(id);
    }
}
