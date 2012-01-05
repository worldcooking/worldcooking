package org.worldcooking.server.services.registration.model;

public class NewParticipant {
	private String name;
	private Long taskId;
	private String emailAddress;

	public NewParticipant(String emailAddress, String name, Long taskId) {
		super();
		this.emailAddress = emailAddress;
		this.name = name;
		this.taskId = taskId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
