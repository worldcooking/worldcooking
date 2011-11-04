/**
 * 
 */
package org.worldcooking.web.worldcooking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	private List<TaskModel> tasks = new ArrayList<TaskModel>();

	private int nbParticipantsMax = 0;

	public int getNbParticipants() {
		return validatedParticipantsTask.size();
	}

	public int getNbParticipantsWaiting() {
		return waitingParticipants.size();
	}

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

	public void addWaitingParticipants(List<String> participants) {
		waitingParticipants.addAll(participants);
	}

	public Set<ParticipantTask> getValidatedParticipantsTask() {
		return validatedParticipantsTask;
	}

	public void addValidatedParticipantTask(String name, Long taskId, Long id) {
		validatedParticipantsTask.add(new ParticipantTask(taskId, name, id));
	}

	public List<TaskModel> getTasks() {
		return tasks;
	}

	public void addTask(TaskModel task) {
		tasks.add(task);
	}

	public void addTask(Long id, String name, int totalRegister, int totalMax) {
		tasks.add(new TaskModel(id, name, totalRegister, totalMax));
	}

	public int getNbParticipantsMax() {
		return nbParticipantsMax;
	}

	public void setNbParticipantsMax(int nbParticipantsMax) {
		this.nbParticipantsMax = nbParticipantsMax;
	}

	public void addTasks(List<TaskModel> tasks) {
		this.tasks.addAll(tasks);

	}

}
