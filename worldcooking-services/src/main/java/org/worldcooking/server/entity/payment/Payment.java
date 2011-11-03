package org.worldcooking.server.entity.payment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author MatthieuG
 * 
 */
@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column
	private Date perceptionTime;

	@Column
	private Double amount;

	@Column
	private PaymentMode mode;

	@Column
	private String reference;

	public PaymentMode getMode() {
		return mode;
	}

	public void setMode(PaymentMode mode) {
		this.mode = mode;
	}

	public Date getPerceptionTime() {
		return perceptionTime;
	}

	public void setPerceptionTime(Date perceptionTime) {
		this.perceptionTime = perceptionTime;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String target) {
		this.reference = target;
	}

}
