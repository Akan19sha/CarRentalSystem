package com.example.demo.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Billing {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int reservationId;
    private BigDecimal amount;
    private LocalDate paymentDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	/*@Override
	public String toString() {
		return "Billing [id=" + id + ", reservationId=" + reservationId + ", amount=" + amount + ", paymentDate="
				+ paymentDate + "]";
	}*/
	public Billing(){
		//constructor
	}
	public Billing(int reservationId, BigDecimal amount, LocalDate paymentDate) {
		super();
		this.reservationId = reservationId;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}
    
}
