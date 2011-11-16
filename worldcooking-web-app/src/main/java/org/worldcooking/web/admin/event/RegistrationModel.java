package org.worldcooking.web.admin.event;

import java.util.List;

public class RegistrationModel {

	private Long id;

	private Registrer registrer;

	private Double amount;

	private List<AdditionalParticipant> additionalParticipants;

	public Registrer getRegistrer() {
		return registrer;
	}

	public void setRegistrer(Registrer registrer) {
		this.registrer = registrer;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<AdditionalParticipant> getAdditionalParticipants() {
		return additionalParticipants;
	}

	public void setAdditionalParticipants(
			List<AdditionalParticipant> additionalParticipants) {
		this.additionalParticipants = additionalParticipants;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
