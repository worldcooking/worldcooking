package org.worldcooking.server.entity.paiement;

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

}
