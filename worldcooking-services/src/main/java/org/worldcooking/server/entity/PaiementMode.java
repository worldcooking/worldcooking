package org.worldcooking.server.entity;

/**
 * 
 * @author MatthieuG
 *
 */
public abstract class PaiementMode {
	
	private boolean online;

	public PaiementMode(boolean online) {
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
