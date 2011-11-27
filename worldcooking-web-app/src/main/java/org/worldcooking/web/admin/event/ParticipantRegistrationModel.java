package org.worldcooking.web.admin.event;

public class ParticipantRegistrationModel {
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
