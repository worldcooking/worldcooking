/**
 * 
 */
package org.worldcooking.server.entity.people;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.worldcooking.server.entity.event.Registration;
import org.worldcooking.server.entity.event.Task;

/**
 * @author MatthieuG
 * 
 */
@Entity
public class Participant {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column
	private String email;

	@ManyToOne
	private Task task;

	@ManyToOne
	private Registration registration;

	public Participant() {
		// nothing to do
	}

	public Participant(String name, Task task) {
		super();
		this.name = name;
		this.task = task;
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
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * @param task
	 *            the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
