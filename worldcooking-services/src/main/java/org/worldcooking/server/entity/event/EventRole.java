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
 * Mapping event-role-participants.
 * 
 * @author MatthieuG
 * 
 */
@Entity
public class EventRole {

	/**
	 * Unique id in DB.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private Event event;

	@ManyToOne
	private Role role;

	@Column
	private Integer nbMax;

	@Column
	private Double pricePerParticipant;

	@OneToMany(mappedBy = "eventRole")
	private List<Participant> participants = new ArrayList<Participant>();

	public EventRole() {
	}

	public void addParticipant(Participant participant) {
		this.participants.add(participant);
		participant.setEventRole(this);
	}

	public Long getId() {
		return id;
	}

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

	public Double getPricePerParticipant() {
		return pricePerParticipant;
	}

	public void setPricePerParticipant(Double pricePerParticipant) {
		this.pricePerParticipant = pricePerParticipant;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
