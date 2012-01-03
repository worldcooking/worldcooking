package org.worldcooking.web.worldcooking.admin.events.model;

public class EventToEditParticipants {
	private Long current;
	private Long max;
	private Long pending;

	public EventToEditParticipants(Long current, Long max, Long pending) {
		super();
		this.current = current;
		this.max = max;
		this.pending = pending;
	}

	public Long getCurrent() {
		return current;
	}

	public void setCurrent(Long current) {
		this.current = current;
	}

	public Long getMax() {
		return max;
	}

	public void setMax(Long max) {
		this.max = max;
	}

	public Long getPending() {
		return pending;
	}

	public void setPending(Long pending) {
		this.pending = pending;
	}
}
