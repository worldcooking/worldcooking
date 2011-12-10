/**
 * 
 */
package org.worldcooking.web.worldcooking.resume.model;

/**
 * @author MatthieuG
 * 
 */
public class WorldcookingResumeParticipantTask {

	private Long taskId;

	private String name;

	private Long id;

	public WorldcookingResumeParticipantTask(Long taskId, String name, Long participantId) {
		this.taskId = taskId;
		this.name = name;
		this.id = participantId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

}
