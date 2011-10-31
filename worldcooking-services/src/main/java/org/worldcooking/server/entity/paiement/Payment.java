package org.worldcooking.server.entity.paiement;

import java.util.Date;

/**
 * 
 * @author MatthieuG
 *
 */
public class Payment {
	
	private PaymentMode mode;
	
	private Date perceptionTime;
	
	private Double amount;

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
	
}
