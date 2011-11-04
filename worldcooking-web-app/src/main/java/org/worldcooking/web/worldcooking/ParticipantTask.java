/**
 * 
 */
package org.worldcooking.web.worldcooking;

/**
 * @author MatthieuG
 * 
 */
public class ParticipantTask {

	private Long taskId;

	private String name;

	private Long id;

	public ParticipantTask(Long taskId, String name, Long participantId) {
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
