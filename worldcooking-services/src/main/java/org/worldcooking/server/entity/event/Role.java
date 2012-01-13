/**
 * 
 */
package org.worldcooking.server.entity.event;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.worldcooking.server.entity.people.Participant;

/**
 * A task is use to describe a role on an event.
 * 
 * @author MatthieuG
 * 
 */
@Entity
public class Role {

	/**
	 * Unique id in DB.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	/** Short text use to identify the task. */
	@Column(nullable = false)
	private String name;

	/** Description for this task. */
	@Column
	private String description;

	/** Event associated to this task. */
	@ManyToOne
	private Event event;

	@Column
	private Integer nbMax;

	@Column
	private Double pricePerParticipant;

	@OneToMany(mappedBy = "role")
	private List<Participant> participants = new ArrayList<Participant>();

	@OneToMany(mappedBy = "role")
	private List<Task> tasks = new ArrayList<Task>();

	public Role() {
		// nothing to do
	}

	public Role(String name, String description, Integer nbMax) {
		super();
		this.name = name;
		this.description = description;
		this.nbMax = nbMax;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Integer getNbMax() {
		return nbMax;
	}

	public void setNbMax(Integer nbMax) {
		this.nbMax = nbMax;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	/**
	 * @use addParticipant instead
	 * @param participants
	 */
	protected void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public void addParticipant(Participant participant) {
		this.participants.add(participant);
	}

	public void addTask(Task task) {
		this.tasks.add(task);
		task.setRole(this);
	}

	public Double getPricePerParticipant() {
		return pricePerParticipant;
	}

	public void setPricePerParticipant(Double participantPrice) {
		this.pricePerParticipant = participantPrice;
	}

}
