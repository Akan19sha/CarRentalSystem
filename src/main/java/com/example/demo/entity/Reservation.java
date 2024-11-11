package com.example.demo.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
@Entity
public class Reservation {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    
	    @ManyToOne
	    private Car car;
	    
	    private LocalDate startDate;
	    private LocalDate endDate;
	    private BigDecimal totalPrice;
	    private String customerName;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Car getCar() {
			return car;
		}
		public void setCar(Car car) {
			this.car = car;
		}
		public LocalDate getStartDate() {
			return startDate;
		}
		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}
		public LocalDate getEndDate() {
			return endDate;
		}
		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}
		public BigDecimal getTotalPrice() {
			return totalPrice;
		}
		public void setTotalPrice(BigDecimal totalPrice) {
			this.totalPrice = totalPrice;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		
		public Reservation(int id,String customerName, LocalDate date, LocalDate date2, BigDecimal d,
				Car car) {
			super();
			this.car = car;
			this.startDate = date;
			this.endDate = date2;
			this.totalPrice = d;
			this.customerName = customerName;
		}
		/*
		@Override
		public String toString() {
			return "Reservation [id=" + id + ", car=" + car + ", startDate=" + startDate + ", endDate=" + endDate
					+ ", totalPrice=" + totalPrice + ", customerName=" + customerName + "]";
		}
	    */
}
