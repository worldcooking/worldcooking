package org.worldcooking.web.worldcooking.admin.event.participants.model;

import java.util.List;

public class WorldcookingAdminEventRegistration {

	private Long id;

	private WorldcookingAdminEventParticipant registrer;

	private Double amount;

	private String paymentDescription;

	private Boolean validated = false;

	private List<WorldcookingAdminEventParticipant> additionalParticipants;

	public WorldcookingAdminEventParticipant getRegistrer() {
		return registrer;
	}

	public void setRegistrer(WorldcookingAdminEventParticipant registrer) {
		this.registrer = registrer;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<WorldcookingAdminEventParticipant> getAdditionalParticipants() {
		return additionalParticipants;
	}

	public void setAdditionalParticipants(List<WorldcookingAdminEventParticipant> additionalParticipants) {
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

	public Boolean getValidated() {
		return validated;
	}

	public void setValidated(Boolean validated) {
		this.validated = validated;
	}
}
