package org.worldcooking.web.worldcooking.admin.event.model;

public class WorldcookingAdminEventParticipantRegistration {
	private Long taskId;
	private Double newAmount;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Double getNewAmount() {
		return newAmount;
	}

	public void setNewAmount(Double newAmount) {
		this.newAmount = newAmount;
	}
}
