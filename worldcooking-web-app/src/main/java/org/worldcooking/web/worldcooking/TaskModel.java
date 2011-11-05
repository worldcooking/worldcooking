/**
 * 
 */
package org.worldcooking.web.worldcooking;

import org.dozer.Mapping;

/**
 * @author MatthieuG
 * 
 */
public class TaskModel {

	private Long id;

	private String name;

	private int totalRegister = 0;

	private int totalMax = 0;

	public TaskModel() {

	}

	public TaskModel(Long id, String name, int totalRegister, int totalMax) {
		super();
		this.id = id;
		this.name = name;
		this.totalRegister = totalRegister;
		this.totalMax = totalMax;
	}

	@Mapping("id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Mapping("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalRegister() {
		return totalRegister;
	}

	public void setTotalRegister(int totalRegister) {
		this.totalRegister = totalRegister;
	}

	public int getTotalMax() {
		return totalMax;
	}

	public void setTotalMax(int totalMax) {
		this.totalMax = totalMax;
	}

}
