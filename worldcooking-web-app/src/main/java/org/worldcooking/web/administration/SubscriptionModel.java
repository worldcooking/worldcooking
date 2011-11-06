package org.worldcooking.web.administration;

import org.dozer.Mapping;

public class SubscriptionModel {

	private Long id;

	/** Email use for the registration. */
	private String email;

	private Double amount;

	public Long getId() {
		return id;
	}

	@Mapping("id")
	public void setId(Long id) {
		this.id = id;
	}

	@Mapping("email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Mapping("payment.amount")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
