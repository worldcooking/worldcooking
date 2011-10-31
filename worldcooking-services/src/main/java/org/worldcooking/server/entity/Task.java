/**
 * 
 */
package org.worldcooking.server.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A task is use to describe a role on an event.
 * 
 * @author MatthieuG
 *
 */
public class Task {
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;
	
	/** Short text use to identify the task. */
	@Column
	private String name;
	
	/** Description for this task. */
	@Column
	private String description;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
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
	 * @param description the description to set
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
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
