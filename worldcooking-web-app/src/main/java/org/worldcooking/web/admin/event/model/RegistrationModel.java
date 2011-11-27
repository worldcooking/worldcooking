package org.worldcooking.web.admin.event.model;

import java.util.List;

public class RegistrationModel {

	private Long id;

	private RegistrerModel registrer;

	private Double amount;

	private String paymentDescription;

	private List<ParticipantModel> additionalParticipants;

	public RegistrerModel getRegistrer() {
		return registrer;
	}

	public void setRegistrer(RegistrerModel registrer) {
		this.registrer = registrer;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<ParticipantModel> getAdditionalParticipants() {
		return additionalParticipants;
	}

	public void setAdditionalParticipants(
			List<ParticipantModel> additionalParticipants) {
		this.additionalParticipants = additionalParticipants;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaymentDescription() {
		return paymentDescription;
	}

	public void setPaymentDescription(String paymentDescription) {
		this.paymentDescription = paymentDescription;
	}
}
