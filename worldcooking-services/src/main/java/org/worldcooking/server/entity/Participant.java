/**
 * 
 */
package org.worldcooking.server.entity;

/**
 * @author MatthieuG
 *
 */
public class Participant {
	
	private String name;
	
	private Task task;

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
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}
	
	

}
