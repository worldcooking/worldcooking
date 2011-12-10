/**
 * 
 */
package org.worldcooking.web.worldcooking.resume.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.dozer.Mapping;

/**
 * @author MatthieuG
 * 
 */
public class WorldcookingResumeEvent {

	/** Name of the event. */

	private String name;

	/** General information / description. */
	private String information;

	/** Participants waiting for validation. */
	private List<String> waitingParticipants = new ArrayList<String>();

	/** Participants validated associated to their task id. */
	private Set<WorldcookingResumeParticipantTask> validatedParticipantsTask = new HashSet<WorldcookingResumeParticipantTask>();

	/** List of task with their ids. */
	private List<WorldcookingResumeTask> tasks = new ArrayList<WorldcookingResumeTask>();

	private int nbParticipantsMax = 0;

	private Boolean registrationClosed;

	public int getNbParticipants() {
		return validatedParticipantsTask.size();
	}

	public int getNbParticipantsWaiting() {
		return waitingParticipants.size();
	}

	@Mapping("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Mapping("description")
	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public List<String> getWaitingParticipants() {
		Collections.sort(waitingParticipants, new IgnoreCaseStringComparator());
		return waitingParticipants;
	}

	public void addWaitingParticipant(String participant) {
		waitingParticipants.add(participant);
	}

	public void addWaitingParticipants(List<String> participants) {
		waitingParticipants.addAll(participants);
	}

	public Set<WorldcookingResumeParticipantTask> getValidatedParticipantsTask() {
		return validatedParticipantsTask;
	}

	public List<WorldcookingResumeParticipantTask> getValidatedParticipantsTaskOrdered() {
		List<WorldcookingResumeParticipantTask> participants = new ArrayList<WorldcookingResumeParticipantTask>(
				validatedParticipantsTask);

		Collections.sort(participants, ParticipantTaskComparator.getInstance());

		return participants;
	}

	public void addValidatedParticipantTask(String name, Long taskId, Long id) {
		validatedParticipantsTask.add(new WorldcookingResumeParticipantTask(taskId, name, id));
	}

	@Mapping("availableTasks")
	public List<WorldcookingResumeTask> getTasks() {
		Collections.sort(tasks, TaskComparator.getInstance());
		return tasks;
	}

	public void addTask(WorldcookingResumeTask task) {
		tasks.add(task);
	}

	public void addTask(Long id, String name, int totalRegister, int totalMax) {
		tasks.add(new WorldcookingResumeTask(id, name, totalRegister, totalMax));
	}

	public void setWaitingParticipants(List<String> waitingParticipants) {
		this.waitingParticipants = waitingParticipants;
	}

	public void setValidatedParticipantsTask(
			Set<WorldcookingResumeParticipantTask> validatedParticipantsTask) {
		this.validatedParticipantsTask = validatedParticipantsTask;
	}

	public void setTasks(List<WorldcookingResumeTask> tasks) {
		this.tasks = tasks;
	}

	@Mapping("maxParticipants")
	public int getNbParticipantsMax() {
		return nbParticipantsMax;
	}

	public void setNbParticipantsMax(int nbParticipantsMax) {
		this.nbParticipantsMax = nbParticipantsMax;
	}

	public void addTasks(List<WorldcookingResumeTask> tasks) {
		this.tasks.addAll(tasks);
	}

	private final class IgnoreCaseStringComparator implements
			Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			return new CompareToBuilder().append(o1.toLowerCase(),
					o2.toLowerCase()).toComparison();
		}
	}

	private static class TaskComparator implements Comparator<WorldcookingResumeTask> {

		private static TaskComparator instance;

		private TaskComparator() {

		}

		public static TaskComparator getInstance() {
			if (instance == null) {
				instance = new TaskComparator();
			}
			return instance;
		}

		@Override
		public int compare(WorldcookingResumeTask o1, WorldcookingResumeTask o2) {

			return o1.getName().compareTo(o2.getName());
		}

	}

	private static class ParticipantTaskComparator implements
			Comparator<WorldcookingResumeParticipantTask> {

		private static ParticipantTaskComparator instance;

		private ParticipantTaskComparator() {

		}

		public static ParticipantTaskComparator getInstance() {
			if (instance == null) {
				instance = new ParticipantTaskComparator();
			}
			return instance;
		}

		@Override
		public int compare(WorldcookingResumeParticipantTask o1, WorldcookingResumeParticipantTask o2) {

			return o1.getName().toLowerCase()
					.compareTo(o2.getName().toLowerCase());
		}

	}

	public Boolean getRegistrationClosed() {
		return registrationClosed;
	}

	public void setRegistrationClosed(Boolean registrationClosed) {
		this.registrationClosed = registrationClosed;
	}

}
