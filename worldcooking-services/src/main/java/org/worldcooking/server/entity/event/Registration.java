package org.worldcooking.server.entity.event;

import java.util.Collection;
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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.worldcooking.server.entity.payment.Payment;
import org.worldcooking.server.entity.people.Participant;

/**
 * A registration is an association between participants and event.
 * 
 * @author MatthieuG
 * 
 */
@Entity
public class Registration {

	/**
	 * Identify a registration in DB.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	/** Email use for the registration. */
	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private Date registrationDate;

	/** If the registration is validated. */
	@Column(nullable = false)
	private Boolean validate = false;

	/** How this registration is paid. */
	@OneToOne(mappedBy = "registration")
	@Cascade({ CascadeType.DELETE })
	private Payment payment;

	@OneToOne
	private Participant subscriberParticipant;

	/** Event associated to this registration. */
	@ManyToOne
	private Event event;

	/** People registered with this registration. */
	@OneToMany(mappedBy = "registration")
	@OrderBy(value = "name")
	@Cascade({ CascadeType.DELETE })
	private Set<Participant> participants = new HashSet<Participant>();

	public Registration() {
		// nothing to do
	}

	public Registration(String email, Payment payment, Event event) {
		this.email = email;
		this.payment = payment;
		this.event = event;
		this.registrationDate = new Date();
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the payment
	 */
	public Payment getPayment() {
		return payment;
	}

	/**
	 * @param payment
	 *            the payment to set
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
		if (payment.getRegistration() == null
				|| payment.getRegistration() != this) {
			payment.setRegistration(this);
		}
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	public Set<Participant> getParticipants() {
		return participants;
	}

	protected void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	public void addParticipant(Participant participant) {
		participant.setRegistration(this);
		participants.add(participant);
	}

	public void addParticipants(Collection<Participant> newParticipants) {
		for (Participant participant : newParticipants) {
			participant.setRegistration(this);
		}
		participants.addAll(newParticipants);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getValidate() {
		return validate;
	}

	public void setValidate(Boolean validate) {
		this.validate = validate;
	}

	public Participant getSubscriberParticipant() {
		return subscriberParticipant;
	}

	public void setSubscriberParticipant(Participant subscriber) {
		this.subscriberParticipant = subscriber;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

}
