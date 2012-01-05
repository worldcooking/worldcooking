package org.worldcooking.web.worldcooking.admin.event.participants.model;

public class WorldcookingAdminEventParticipant {
	private String name;
	private Long id;
	private String email;

	private WorldcookingAdminEventTask task;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String emailAddress) {
		this.email = emailAddress;
	}

	public WorldcookingAdminEventTask getTask() {
		return task;
	}

	public void setTask(WorldcookingAdminEventTask task) {
		this.task = task;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
