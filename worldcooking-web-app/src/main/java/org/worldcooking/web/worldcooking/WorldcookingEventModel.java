/**
 * 
 */
package org.worldcooking.web.worldcooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author MatthieuG
 * 
 */
public class WorldcookingEventModel {

	/** Name of the event. */
	private String name;

	/** General information / description. */
	private String information;

	/** Participants waiting for validation. */
	private List<String> waitingParticipants = new ArrayList<String>();

	/** Participants validated associated to their task id. */
	private Set<ParticipantTask> validatedParticipantsTask = new HashSet<ParticipantTask>();

	/** List of task with their ids. */
	private Map<Long, String> tasks = new HashMap<Long, String>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public List<String> getWaitingParticipants() {
		return waitingParticipants;
	}

	public void addWaitingParticipant(String participant) {
		waitingParticipants.add(participant);
	}

	public void addWaitingParticipant(List<String> participants) {
		waitingParticipants.addAll(participants);
	}

	public Set<ParticipantTask> getValidatedParticipantsTask() {
		return validatedParticipantsTask;
	}

	public void addValidatedParticipantsTask(String name, Long taskId) {
		validatedParticipantsTask.add(new ParticipantTask(taskId, name));
	}

	public Map<Long, String> getTasks() {
		return tasks;
	}
	
	public void addTask(Long taskId, String name) {
		tasks.put(taskId, name);
	}

	private class ParticipantTask {

		private Long taskId;

		private String name;

		public ParticipantTask(Long taskId, String name) {
			this.taskId = taskId;
			this.name = name;
		}
	}

}
