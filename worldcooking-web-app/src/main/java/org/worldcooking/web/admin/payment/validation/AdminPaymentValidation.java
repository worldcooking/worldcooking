package org.worldcooking.web.admin.payment.validation;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class AdminPaymentValidation {

	@Min(value = 0, message = "Please choose the registration to validate")
	private Long registrationId;

	@NotEmpty(message = "Please enter your password")
	private String password;

	public Long getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(Long registrationId) {
		this.registrationId = registrationId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
