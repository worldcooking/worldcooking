/**
 * 
 */
package org.worldcooking.web.worldcooking.resume.model;

import org.dozer.Mapping;

/**
 * @author MatthieuG
 * 
 */
public class WorldcookingResumeTask {

	private Long id;

	private String name;

	private long totalRegister = 0;

	private long totalMax = 0;

	public WorldcookingResumeTask() {

	}

	public WorldcookingResumeTask(Long id, String name, long totalRegister, long totalMax) {
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

	public long getTotalRegister() {
		return totalRegister;
	}

	public void setTotalRegister(long totalRegister) {
		this.totalRegister = totalRegister;
	}

	@Mapping("nbMax")
	public long getTotalMax() {
		return totalMax;
	}

	public void setTotalMax(long totalMax) {
		this.totalMax = totalMax;
	}

}
