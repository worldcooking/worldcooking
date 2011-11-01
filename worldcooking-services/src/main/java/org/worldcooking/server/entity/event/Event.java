package org.worldcooking.server.entity.event;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column
	private String name;

	@Column
	private String description;

	@OneToMany(mappedBy = "event")
	private List<Subscription> subscriptions = new ArrayList<Subscription>();

	@OneToMany(mappedBy = "event")
	private List<Task> availableTasks = new ArrayList<Task>();

	@Column
	private Integer maxParticipants;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	protected void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public void addSubscription(Subscription subscription) {
		this.subscriptions.add(subscription);
		subscription.setEvent(this);
	}

	public List<Task> getAvailableTasks() {
		return availableTasks;
	}

	/**
	 * Use addAvailableTask instead
	 * 
	 * @param availableTasks
	 */
	protected void setAvailableTasks(List<Task> availableTasks) {
		this.availableTasks = availableTasks;
	}

	public void addAvailableTask(Task task) {
		this.availableTasks.add(task);
		task.setEvent(this);
	}

	public Integer getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(Integer maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

}
