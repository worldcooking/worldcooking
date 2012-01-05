package org.worldcooking.server.entity.event;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.worldcooking.server.entity.people.Participant;
import org.worldcooking.server.entity.place.Place;

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

	public enum RegistrationStatus {
		/**
		 * Event not visible to public.
		 */
		HIDDEN,
		/**
		 * Event visible to public, but registration still not open.
		 */
		PLANNED,
		/**
		 * Event open for registration.
		 */
		OPEN,
		/**
		 * Registration closed.
		 */
		CLOSED;
	}

	/**
	 * Registration status (HIDDEN, PLANNED, OPEN, CLOSED).
	 */
	@Column(nullable = false)
	private RegistrationStatus registrationStatus = RegistrationStatus.HIDDEN;

	public RegistrationStatus getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(RegistrationStatus registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	/**
	 * Unique reference.
	 */
	@Column(unique = true, nullable = false)
	private String reference;

	/**
	 * Event name use to identify the event for the users.
	 */
	@Column
	private String name;

	/**
	 * Date and time of event.
	 */
	@Column
	private Date dateTime;

	/**
	 * List of all the action of registration done by users. Can contain
	 * validated et non-validated registrations.
	 */
	@OneToMany(mappedBy = "event")
	@OrderBy(value = "registrationDate")
	@Cascade({ CascadeType.DELETE })
	private Set<Registration> registrations = new HashSet<Registration>();

	/**
	 * Tasks available for this event. Only those tasks can be affected to the
	 * participants of the event.
	 * 
	 * @see Participant
	 */
	@OneToMany(mappedBy = "event")
	@Cascade({ CascadeType.DELETE })
	@OrderBy(value = "name")
	private Set<Task> availableTasks = new HashSet<Task>();

	@ManyToOne
	private Place place;

	/**
	 * Maximum of participants validated for this event.
	 */
	@Column
	private Long maxParticipants;

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

	public Set<Registration> getRegistrations() {
		return registrations;
	}

	protected void setRegistrations(Set<Registration> registrations) {
		this.registrations = registrations;
	}

	public void addRegistration(Registration registration) {
		this.registrations.add(registration);
		registration.setEvent(this);
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

	public Long getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(Long maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}
}
