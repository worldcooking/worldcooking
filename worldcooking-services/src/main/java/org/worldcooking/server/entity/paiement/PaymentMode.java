package org.worldcooking.server.entity.paiement;

/**
 * 
 * @author MatthieuG
 *
 */
public abstract class PaymentMode {
	
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
	 * @param online the online to set
	 */
	public void setOnline(boolean online) {
		this.online = online;
	}
	
	

}
