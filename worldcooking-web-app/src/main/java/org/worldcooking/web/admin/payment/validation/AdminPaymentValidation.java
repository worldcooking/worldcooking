package org.worldcooking.web.admin.payment.validation;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class AdminPaymentValidation {

	@Min(value = 0, message = "Please choose the subscription to validate")
	private Long subscriptionId;

	@NotEmpty(message = "Please enter your password")
	private String password;

	public Long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
