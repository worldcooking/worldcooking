/**
 * 
 */
package org.worldcooking.server.entity.event;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Role task.
 * 
 * @author MatthieuG
 * 
 */
@Entity
public class Task {

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

	@Column
	private String startTimeDescription;

	@Column
	private String endTimeDescription;

	@Column
	private Date minStartTime;

	@Column
	private Date maxEndDateTime;

	@ManyToOne
	private Role role;

	public Task() {
		// nothing to do
	}

	public Task(String name, String description) {
		super();
		this.name = name;
		this.description = description;
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

	public String getStartTimeDescription() {
		return startTimeDescription;
	}

	public void setStartTimeDescription(String startTimeDescription) {
		this.startTimeDescription = startTimeDescription;
	}

	public String getEndTimeDescription() {
		return endTimeDescription;
	}

	public void setEndTimeDescription(String endTimeDescription) {
		this.endTimeDescription = endTimeDescription;
	}

	public Date getMinStartTime() {
		return minStartTime;
	}

	public void setMinStartTime(Date minStartTime) {
		this.minStartTime = minStartTime;
	}

	public Date getMaxEndDateTime() {
		return maxEndDateTime;
	}

	public void setMaxEndDateTime(Date maxEndDateTime) {
		this.maxEndDateTime = maxEndDateTime;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
