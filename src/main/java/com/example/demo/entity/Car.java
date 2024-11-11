package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Car {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String make;
    private String model;
    private String licensePlate;
    private boolean available;
    
    public Car()
    {
    	//constructor
    }
    public Car(int id ,String make, String model, boolean available) {
		super();
		this.make = make;
		this.model = model;
		this.available = available;
	}
    
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	/*@Override
	public String toString() {
		return "Car [id=" + id + ", make=" + make + ", model=" + model + ", licensePlate=" + licensePlate
				+ ", available=" + available + "]";
	}*/
    
}
