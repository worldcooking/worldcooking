package org.worldcooking.web.admin.event.model;

public class ParticipantModel {
	private String name;
	private Long id;

	private TaskModel task;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TaskModel getTask() {
		return task;
	}

	public void setTask(TaskModel task) {
		this.task = task;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
