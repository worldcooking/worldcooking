package org.worldcooking.web.worldcooking.admin.event.participants.model;

public class WorldcookingAdminEventParticipantRegistration {
	private Long eventRoleId;
	private Double newAmount;

	public Long getEventRoleId() {
		return eventRoleId;
	}

	public void setEventRoleId(Long taskId) {
		this.eventRoleId = taskId;
	}

	public Double getNewAmount() {
		return newAmount;
	}

	public void setNewAmount(Double newAmount) {
		this.newAmount = newAmount;
	}
}
