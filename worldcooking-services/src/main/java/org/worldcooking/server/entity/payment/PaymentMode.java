package org.worldcooking.server.entity.payment;


/**
 * 
 * @author MatthieuG
 * 
 */
public abstract class PaymentMode {

	private Long id;

	private boolean online;

	public PaymentMode(boolean online) {
		this.online = online;
	}

	/**
	 * @return the online
	 */
	public boolean isOnline() {
		return online;
	}

	/**
	 * @param online
	 *            the online to set
	 */
	public void setOnline(boolean online) {
		this.online = online;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
