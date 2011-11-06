package org.worldcooking.server.entity.event;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.worldcooking.server.entity.people.Participant;

/**
 * Describes an event.
 * 
 * @author MatthieuG
 * 
 */
@Entity
public class Event {

	/**
	 * Unique id use to identify the Event in db.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	/**
	 * Event name use to identify the event for the users.
	 */
	@Column
	private String name;

	/**
	 * Text description of the event. Can contain html tags.
	 */
	@Column(length = 10000)
	private String description;

	/**
	 * List of all the action of subscription done by users. Can contain
	 * validated et non-validated subscriptions.
	 */
	@OneToMany(mappedBy = "event")
	@Cascade({ CascadeType.DELETE })
	private Set<Subscription> subscriptions = new HashSet<Subscription>();

	/**
	 * Tasks available for this event. Only those tasks can be affected to the
	 * participants of the event.
	 * 
	 * @see Participant
	 */
	@OneToMany(mappedBy = "event")
	@Cascade({ CascadeType.DELETE })
	private Set<Task> availableTasks = new HashSet<Task>();

	/**
	 * Maximum of participants validated for this event.
	 */
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

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	protected void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public void addSubscription(Subscription subscription) {
		this.subscriptions.add(subscription);
		subscription.setEvent(this);
	}

	public Set<Task> getAvailableTasks() {
		return availableTasks;
	}

	protected void setAvailableTasks(Set<Task> availableTasks) {
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
