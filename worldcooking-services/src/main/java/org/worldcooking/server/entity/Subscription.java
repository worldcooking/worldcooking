package org.worldcooking.server.entity;

import javax.persistence.Column;

/**
 * Association 
 * 
 * @author MatthieuG
 *
 */
public class Subscription {
	
	/** Email use for the registration. */
	@Column
	private String email;
	
	private Paiement paiement;
	
	private Event event;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the paiement
	 */
	public Paiement getPaiement() {
		return paiement;
	}

	/**
	 * @param paiement the paiement to set
	 */
	public void setPaiement(Paiement paiement) {
		this.paiement = paiement;
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}
	

}
