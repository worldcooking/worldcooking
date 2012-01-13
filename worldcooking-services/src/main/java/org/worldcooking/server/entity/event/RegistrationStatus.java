package org.worldcooking.server.entity.event;

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